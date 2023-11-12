package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassyTreeMouseListener extends MouseAdapter {

    private JTree tree;

    public ClassyTreeMouseListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            if (path != null && path.getLastPathComponent() instanceof ClassyTreeItem) {
                ClassyTreeItem item = (ClassyTreeItem) path.getLastPathComponent();
                if (item.getClassyNode() instanceof Package) {
                    Package pkg = (Package) item.getClassyNode();
                    MainFrame.getInstance().getPackageView().subscribeToPackage(pkg);
                }
            }
        }
    }
}
