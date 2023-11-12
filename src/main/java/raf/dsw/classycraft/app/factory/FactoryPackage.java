package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;


public class FactoryPackage extends FactoryAbstract{
    public FactoryPackage(String name, ClassyNode parent) {
        super(name, parent);
    }
    static int i=1;

    public static int getI() {
        return i++;
    }

    @Override
    public ClassyNode createNode(ClassyNode parent) {
        String packageName = "Package" + i++;
        return new Package(packageName, parent);
    }
}
