/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.uiController;

import com.gisbim.simpleAuthWeb.IBatisFactory;
import com.gisbim.simpleAuthWeb.UserInfo;
import com.gisbim.simpleAuthWeb.persist.Fun;
import com.gisbim.simpleAuthWeb.persist.Role;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

/**
 *
 * @author jeff
 */
public class MainController extends SelectorComposer<Component> {

    String loginUrl = "rcv.zul";
    private static final Logger logger = 
            LoggerFactory.getLogger(MainController.class);
    
    @Wire("#roleName")
    Label roleNameLabel;
    @Wire("#userName")
    Label userNameLabel;
    @Wire("#funTree")
    Tree funTree;
    @Wire("#tbs")
    Tabs tbs;
    @Wire("#tps")
    Tabpanels tps;        

    public void doAfterCompose(Component win) {
        try {
            super.doAfterCompose(win);
            // 設定使用者的IP
            getClientInfo();

            // Session 中沒有使用者資料, 退回登入畫面
            if (Sessions.getCurrent().getAttribute("userInfo") == null) {
                Executions.getCurrent().sendRedirect(loginUrl);
            }

            UserInfo info
                    = (UserInfo) Sessions.getCurrent().getAttribute("userInfo");
            List<Role> dutyInfoList = info.getRoles(); // get from session
            Role mainRole = (Role) dutyInfoList.get(0); // get first duty
            roleNameLabel.setValue(mainRole.getRoleName());
            userNameLabel.setValue(info.getUser().getUserName());

            SqlSession session = IBatisFactory.getInstance().getSqlSession();
            List<Fun> funList
                    = session.selectList(
                            "RcvLogin.getFunAuth", mainRole.getId());
            if (funList == null || funList.isEmpty()) {
                throw new LogicException(String.format(Labels.getLabel(
                        "RcvLogin.msg.noFunAuthToTheRole"), 
                        mainRole.getRoleName()));
            }
            logger.info(String.format(
                    "funFormList size: %d  has saved to session",
                    funList.size()));
            info.getRoleWithFunction().put(mainRole, funList);          
            // show the function
            DefaultTreeModel treeModel = 
                    new DefaultTreeModel(prepareRootNode(funList)); 
            funTree.setModel(treeModel);
            

        } catch (LogicException e) { // safe net 
            e.printStackTrace();
            logger.error(e.getMessage());
            Messagebox.show(
                    Labels.getLabel("Common.msg.error.mark") + e.getMessage(),
                    Labels.getLabel("Common.msg.error.tryAgain"),
                    Messagebox.OK, Messagebox.ERROR);
        } catch (Exception e) { // safe net
            e.printStackTrace();
            logger.error(e.getMessage());
            Messagebox.show(
                    Labels.getLabel("Common.msg.error.unexpectedErrorMark") + e.getMessage(),
                    Labels.getLabel("Common.msg.error.pleaseContactDeveloper"),
                    Messagebox.OK, Messagebox.ERROR);
        }
    }

    private DefaultTreeNode prepareRootNode(List funData) {
        Fun root = new Fun();
        root.setFunName("");

        ArrayList mother = new ArrayList();
        Iterator it = funData.iterator();
        while (it.hasNext()) {
            Fun form = (Fun) it.next();
            if ("0".equals(form.getLayer().trim())) {

                ArrayList subList = getSubList(form, funData);
                DefaultTreeNode stn = new DefaultTreeNode(form, subList);
                mother.add(stn);
            }
        }

        DefaultTreeNode rootNode = new DefaultTreeNode(root, mother);

        return rootNode;
    }

    private ArrayList getSubList(Fun theFun, List funData) {
        ArrayList subList = new ArrayList();
        Iterator it = funData.iterator();

        while (it.hasNext()) {
            Fun fun = (Fun) it.next();
            if ((fun.getId() != theFun.getId())
                    && fun.getParentFunId().getId() == theFun.getId()) {
                DefaultTreeNode stn = new DefaultTreeNode(fun, getSubList(
                        fun, funData));
                subList.add(stn);
            }
        }

        return subList;
    }

    private void getClientInfo() {
        Execution exec = getPage().getDesktop().getExecution();
        String clientIP = exec.getRemoteAddr();
        UserInfo info = (UserInfo) Sessions.getCurrent().getAttribute("userInfo");
        info.setClientIp(clientIP);
    }

    private boolean isOpened(String funTab_funId, Tabs tbs) {
        boolean isOpened = false;

        List cs = tbs.getChildren();
        Iterator it = cs.iterator();
        while (it.hasNext()) {
            Tab tab = (Tab) it.next();
            if (funTab_funId.equals(tab.getId())) {
                isOpened = true;
                tab.setSelected(true);
            }
        }
        return isOpened;
    }

    @Listen("onSelect = #funTree")
    public void bootFun() {
        Treeitem ti = funTree.getSelectedItem();
        DefaultTreeNode tn = (DefaultTreeNode)ti.getValue();
        Fun fun = (Fun)tn.getData();
        String funName = fun.getFunName();
        String funPath = fun.getFunPath();
        String funIdStr = fun.getId().toString();         
        String bootFlag = fun.getBootFlag();
        logger.info("------"+funPath);
        // if bootFlat =N , db setter not want you boot this function
        // if the tab is opened, you should not open it again
        // when judging  isOpened, if the tab is opened, set selected,
        if (!"N".equals(bootFlag) && !isOpened("funTab_" + funIdStr, tbs)) {
logger.info("-----------");
            Tab ntb = new Tab(funName);
            ntb.setId("funTab_" + funIdStr);
            ntb.setClosable(true);

            Tabpanel ntp = new Tabpanel();
            ntp.setId("funPanel_" + funIdStr);

            ntb.setParent(tbs);
            ntp.setParent(tps);

            Include inc = new Include(funPath);
            inc.setMode("defer");
            inc.setParent(ntp);

            //Executions.createComponents(funPath, ntp, null);
            ntb.setSelected(true);
        }

    }
    @Listen("onClick = #logout")
    public void logout() {
        // remove data in session
        Sessions.getCurrent().removeAttribute("userInfo");
        Executions.sendRedirect("rcv.zul");
    }
}
