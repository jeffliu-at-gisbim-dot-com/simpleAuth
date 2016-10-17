/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.uiController;

import com.gisbim.simpleAuthWeb.IBatisFactory;
import com.gisbim.simpleAuthWeb.UserInfo;
import com.gisbim.simpleAuthWeb.persist.PriUser;
import com.gisbim.simpleAuthWeb.persist.Role;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jeff
 */
public class RcvLogin extends Window {

    String defaultUrl = "main.zul";
    private static final Logger logger = LoggerFactory.getLogger(RcvLogin.class);
    private static final String ENCRYPT_TYPE_NONE = "0";
    private static final String ENCRYPT_TYPE_MD5  = "1";
    

    public void login() {
        logger.info("User login !!");
        Textbox accountTextbox = (Textbox) this.getFellow("UxAccount");
        Textbox passwordTextbox = (Textbox) this.getFellow("UxPassword");

        String account = accountTextbox.getValue().trim();
        String password = passwordTextbox.getValue().trim();
        logger.info(String.format(Labels.getLabel(
                        "RcvLogin.msg.loginInfo"), account, password));

        try {
            Map para = new HashMap();
            para.put("account", account);
            SqlSession session = IBatisFactory.getInstance().getSqlSession();
          
            // search the User
            Map<String,Object> result = 
                    session.selectMap("RcvLogin.searchUser", para, "");
            
            Map userDataMap = (Map)result.get(null);
            if( userDataMap == null ) {
                throw new LogicException(String.format(Labels.getLabel(
                        "RcvLogin.msg.userNotFound"), account));
            }
            
            switch(userDataMap.get("encrypt_type").toString()) {
                case ENCRYPT_TYPE_NONE:
                    para.put("password", password);
                    break;
                case ENCRYPT_TYPE_MD5:
                    para.put("password", Utility.getInstance().md5(password));           
            }
            
            // validate the password
            PriUser priUser = session.selectOne("RcvLogin.searchUserInfo", para);
            if( priUser == null ) {
                throw new LogicException(String.format((Labels.getLabel(
                        "RcvLogin.msg.wrongPassword")), account));
            } 

            // find out the roles of the user (on Duty as role)
            para.put("now", new Date());
            para.put("user_id", priUser.getId());
            
            List<Role> dutys 
                    = session.selectList("RcvLogin.searchDutyInfo", para);
            if(dutys == null || dutys.isEmpty()) {
                throw new LogicException(String.format(Labels.getLabel(
                        "RcvLogin.msg.dutyNotFound"), account)); 
            }
            if(dutys.size() > 1) {
                 throw new LogicException(String.format(Labels.getLabel(
                        "RcvLogin.msg.mutiRoleNotSupportYet")));   
            }
            
            UserInfo info = new UserInfo();
            info.setUser(priUser);
            info.setRoles(dutys);
            getDesktop().getSession().setAttribute("userInfo", info);
            
            // redirect to main page
            // if the user have only one authType, go to main.zul
            // else, go to multiMain.zul
            if (dutys.size() == 1) {
                Executions.getCurrent().sendRedirect(defaultUrl);
            } else {
                Executions.getCurrent().sendRedirect(defaultUrl);
            }
        } catch (LogicException e) { // safe net 
            logger.error(e.getMessage());
            Messagebox.show(
                    Labels.getLabel("Common.msg.error.mark") + e.getMessage(),
                    Labels.getLabel("Common.msg.error.tryAgain"), 
                    Messagebox.OK, Messagebox.ERROR);
        } catch (Exception e) { // safe net
            logger.error(e.getMessage());
            Messagebox.show(
                    Labels.getLabel("Common.msg.error.unexpectedErrorMark") + e.getMessage(),
                    Labels.getLabel("Common.msg.error.pleaseContactDeveloper"), 
                    Messagebox.OK, Messagebox.ERROR);
        }
    }
}
