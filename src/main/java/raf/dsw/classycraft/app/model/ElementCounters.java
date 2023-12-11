package raf.dsw.classycraft.app.model;

public class ElementCounters {
    private int klasaCount = 1;
    private int enuumCount = 1;
    private int interfejsCount = 1;
    private int agregacijaCount = 1;
    private int kompozicijaCount = 1;
    private int generalizacijaCount= 1;
    private int zavisnostCount= 1;

    public int getNextKlasaCount() {
        return klasaCount++;
    }
    public int getNextEnuumCount() {
        return enuumCount++;
    }
    public int getNextInterfejsCount() {
        return interfejsCount++;
    }
    public int getNextAgregacijaCount(){
        return agregacijaCount++;
    }
    public int getNextKompozicijaCount(){return kompozicijaCount++;}
    public int getNextGeneralizacijaCount(){return generalizacijaCount++;}
    public int getNextZavisnostCount(){return zavisnostCount++;}
}
