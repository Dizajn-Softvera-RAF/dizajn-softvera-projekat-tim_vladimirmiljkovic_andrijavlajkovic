package raf.dsw.classycraft.app.model;

public class ClanEnuma extends ClassContent{
    public ClanEnuma(String naziv) {
        super(naziv);
    }

    @Override
    public String toString() {
        return this.getNaziv();
    }
}
