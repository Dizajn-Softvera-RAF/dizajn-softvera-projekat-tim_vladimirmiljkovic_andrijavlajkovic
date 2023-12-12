package raf.dsw.classycraft.app.repository.composite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import java.util.Objects;

@Getter
@Setter

public abstract class ClassyNode {

    private String name;
    
    private ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ClassyNode) {
            ClassyNode otherObj = (ClassyNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }
    public Diagram findParentDiagram() {
        ClassyNode current = this;
        while (current != null && !(current instanceof Diagram)) {
            current = current.getParent();
        }
        return (Diagram) current;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
