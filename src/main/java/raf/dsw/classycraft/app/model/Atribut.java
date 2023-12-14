package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Atribut extends ClassContent{
    private char vidljivost;
    private TipoviPodataka tip;
    public Atribut(String naziv, char vidljivost, TipoviPodataka tip) {
        super(naziv);
        this.vidljivost = vidljivost;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return this.vidljivost + " " + this.getNaziv() + " " + this.tip;
    }
}
