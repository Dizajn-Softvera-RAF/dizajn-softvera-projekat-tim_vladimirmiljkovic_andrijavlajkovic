package raf.dsw.classycraft.app.state;

public class StateManager {
    private State currentState;
    private DodavanjeICOState dodavanjeICOState;
    private DodavanjeVezeState dodavanjeVezeState;
    private BrisanjeState brisanjeState;
    private SelekcijaState selekcijaState;

    public StateManager() {
        initStates();
    }

    private void initStates() {
        dodavanjeICOState = new DodavanjeICOState();
        dodavanjeVezeState = new DodavanjeVezeState();
        brisanjeState = new BrisanjeState();
        selekcijaState= new SelekcijaState();
        currentState = selekcijaState;
    }

    public State getCurrent(){
        return currentState;
    }

    public void setDodavanjeICOState(){
        currentState = dodavanjeICOState;
    }

    public void setDodavanjeVezeState(){
        currentState = dodavanjeVezeState;
    }

    public void setBrisanjeState(){
        currentState = brisanjeState;
    }
    public void setSelekcijaState(){currentState= selekcijaState;}

}
