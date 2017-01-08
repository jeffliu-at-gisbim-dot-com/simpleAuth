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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

/**
 *
 * @author jeff
 */
public class ParaController extends SelectorComposer<Component> {

    String loginUrl = "rcv.zul";
    private static final Logger logger = 
            LoggerFactory.getLogger(ParaController.class);
    
    @Wire("#paraCatalogue") Combobox paraCatalogueCombobox;
    @Wire("#paraName")      Textbox  paraNameTextbox;
    @Wire("#paraValue")     Textbox  paraValueTextbox;
    @Wire("#paraI18n")      Textbox  paraI18nTextbox;
    @Wire("#addBtn")        Button   addButton;
    @Wire("#update")        Button   updateButton;
    @Wire("#delete")        Button   deleteButton;
    @Wire("#query")         Button   queryButton;
    @Wire("#obj")           Listbox  objListbox;
           

    public void doAfterCompose(Component comp) {
        try {
            //super.doAfterCompose(comp);
            logger.debug("------------------------");
            if (false) {
                throw new LogicException("test");
            }
            
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

}
