package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.util.Random;

public class ClassyTreeImplementation implements  ClassyTree{
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    int i = 1;
    int j = 1;
    int k = 1;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {

        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        ClassyNode child = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(ClassyTreeItem parent) {

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode parent) {
        if (parent instanceof ProjectExplorer) {
            String projectName = "Project" + i++;
            String author = "Author" + i;
            String resourceFolderPath = "Path" + i;
            return new Project(projectName, author, resourceFolderPath, parent);
        }
        else if (parent instanceof Project) {
            String packageName = "Package" + j++;
            String author = "Author" + j;
            String resourceFolderPath = "Path" + j;
            return new Package(packageName, author, resourceFolderPath, parent);
        }
        else if (parent instanceof Package) {
            String diagramName = "Diagram" + k++;
            String author = "Author" + k;
            String resourceFolderPath = "Path" + k;
            return new Diagram(diagramName, parent);
        }
        return null;
    }
}
