package repository.composite;

import lombok.Getter;
import lombok.Setter;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassyNode that = (ClassyNode) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
