package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metod extends ClassContent{
    private char vidljivost;
    private TipoviPodataka tip;
    public Metod(String naziv, char vidljivost, TipoviPodataka tip) {
        super(naziv);
        this.vidljivost = vidljivost;
        this.tip = tip;

    }

    @Override
    public String toString() {
        return vidljivost + " " + this.getNaziv() + "():" + this.tip;
    }
}
