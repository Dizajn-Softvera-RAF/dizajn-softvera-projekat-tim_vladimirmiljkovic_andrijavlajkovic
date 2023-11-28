package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveProjectAction extends AbstractClassyAction{
    public RemoveProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/minus.png"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
    }

    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected != null) {
            ClassyNode selectedNode = selected.getClassyNode();

            // Provera i uklanjanje iz modela
            if (selectedNode.getParent() instanceof ClassyNodeComposite) {
                ClassyNodeComposite parent = (ClassyNodeComposite) selectedNode.getParent();
                parent.removeChild(selectedNode);
            }
            // uklanjanje sa jtree-a
            MainFrame.getInstance().getClassyTree().removeChild(selected);

//            if ((selectedNode instanceof Package && selectedNode.equals(MainFrame.getInstance().getPackageView().getPaket())) ||
//                    (selectedNode instanceof Project && MainFrame.getInstance().getPackageView().getPaket() != null &&
//                            MainFrame.getInstance().getPackageView().getPaket().getParent().equals(selectedNode))) {
//                // Reset PackageView
//                MainFrame.getInstance().getPackageView().resetView();
//            }
        }

    }


}
