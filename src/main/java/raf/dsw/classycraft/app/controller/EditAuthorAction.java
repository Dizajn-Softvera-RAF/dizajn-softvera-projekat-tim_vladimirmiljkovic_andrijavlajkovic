package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.EditAuthorView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAuthorAction extends AbstractClassyAction{

    public EditAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/authors-icon.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit");
    }

    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null)
            return;//MG - poruka da mora da se izabere neki element
        EditAuthorView editAuthorView = new EditAuthorView(null);
        editAuthorView.setVisible(true);
    }
    public void dialogAuthor(String authorName){
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().editAuthor(selected,authorName);
    }
}
