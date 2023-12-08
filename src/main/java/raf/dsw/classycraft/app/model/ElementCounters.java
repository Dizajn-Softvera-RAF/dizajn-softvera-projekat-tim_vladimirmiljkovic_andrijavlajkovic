package raf.dsw.classycraft.app.model;

public class ElementCounters {
    private int classCount = 1;
    private int enumCount = 1;
    private int interfaceCount = 1;

    public int getNextClassCount() {
        return classCount++;
    }

    public int getNextEnumCount() {
        return enumCount++;
    }

    public int getNextInterfaceCount() {
        return interfaceCount++;
    }

    public void reset() {
        classCount = 1;
        enumCount = 1;
        interfaceCount = 1;
    }
}
