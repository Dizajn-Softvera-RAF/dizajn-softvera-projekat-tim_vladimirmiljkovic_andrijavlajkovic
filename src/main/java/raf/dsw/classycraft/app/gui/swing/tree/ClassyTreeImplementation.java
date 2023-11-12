package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.factory.FactoryAbstract;
import raf.dsw.classycraft.app.factory.FactoryUtils;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;


import static raf.dsw.classycraft.app.factory.FactoryPackage.getI;

public class ClassyTreeImplementation implements ClassyTree{
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if(parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Niste izabrali element", MessageType.ERROR);
            return;//MG - mora se izabrati element u koji bi dodali sledeci novi element
        }
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrali ste element koji ne moze da ima dete", MessageType.ERROR);
            return;
        }

        ClassyNode child = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addPackage(ClassyTreeItem parent) {
        if(parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Morate izabrati element u koji bi dodali package", MessageType.ERROR);
            return;//MG - poruka da mora da se izabere element u koji bi dodavali pekidz
        }
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije moguce dodati package u dijagram", MessageType.ERROR);
            return;//MG - nije moguce dodavati pekidz u diagram
        }
        if(parent.getClassyNode().getParent() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije moguce dodati package u project explorer", MessageType.ERROR);
            return;//MG - nije moguce dodavati pekidz u project explorer
        }
        ClassyNode child = createPackage(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void removeChild(ClassyTreeItem parent) {
        if(parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Morate izabrati element za brisanje", MessageType.WARNING);
            return;//MG - mora da se izabere element koji ce da se brise
        }
        if(parent.getClassyNode().getParent() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije moguce brisati project explorer", MessageType.ERROR);
            return;
        }//MG - ne sme da se brise project explorer
        treeModel.removeNodeFromParent(parent);
    }

    @Override
    public void editChild(ClassyTreeItem parent, String name) {
        if(parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije izabran element za uredjivanje", MessageType.WARNING);
            return;//MG - nije izabran element koji treba da se edituje
        }
        if(name == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nije moguce ne postaviti ime", MessageType.ERROR);
            return;//MG - nije moguce ne postaviti ime
        }
        parent.setName(name);
        Refresh();
    }

    private void Refresh(){
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode parent) {
        FactoryAbstract factoryAbstract = FactoryUtils.getFactory(parent);
        return factoryAbstract.getClassyNode(parent);
    }

    private ClassyNode createPackage(ClassyNode parent) {
        if (parent instanceof Project) {
            String packageName = "Package" + getI();
            return new Package(packageName, parent);
        }
        else if (parent instanceof Package) {
            String packageName = "Package" + getI();
            return new Package(packageName, parent);
        }
        return null;
    }

}
