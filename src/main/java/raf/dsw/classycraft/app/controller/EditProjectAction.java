package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.EditView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditProjectAction extends AbstractClassyAction{

    public EditProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit");
    }

    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije selektovan čvor za uređivanje", MessageType.ERROR);
            return;
        }
        EditView editView = new EditView(null);
        editView.setVisible(true);
    }

    public void dialogName(String name){
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije selektovan cvor za uredjivanje", MessageType.ERROR);
            return;
        }

        ClassyNode node = selected.getClassyNode();
        node.setName(name);

//        if (node instanceof Diagram) {
//            ((IPublisher) node).notifySubscribers(new Notification(node, NotificationType.DIAGRAM_NAME_CHANGED));
//        }
//        else if (node instanceof Project) {
//            ((IPublisher) node).notifySubscribers(new Notification(node, NotificationType.PROJECT_NAME_CHANGED));
//        }
        MainFrame.getInstance().getClassyTree().editChild(selected,name);
    }




}
