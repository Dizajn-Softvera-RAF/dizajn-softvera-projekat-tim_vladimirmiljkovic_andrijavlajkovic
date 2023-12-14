package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);
    void removeChild(ClassyTreeItem parent);
    void editChild(ClassyTreeItem parent, String name);
    void addPackage(ClassyTreeItem parent);
    void editAuthor(ClassyTreeItem parent, String authorName);

    ClassyTreeItem getSelectedNode();


}
