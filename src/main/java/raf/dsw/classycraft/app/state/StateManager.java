package raf.dsw.classycraft.app.state;

public class StateManager {
    private State currentState;
    private DodavanjeICOState dodavanjeICOState;
    private DodavanjeVezeState dodavanjeVezeState;
    private BrisanjeState brisanjeState;
    private SelekcijaState selekcijaState;
    private PomeranjeState pomeranjeState;
    private PreuredjivanjeState preuredjivanjeState;
    private DupliranjeState dupliranjeState;
    private ZoomState zoomState;


    public StateManager() {
        initStates();
    }

    private void initStates() {
        dodavanjeICOState = new DodavanjeICOState();
        dodavanjeVezeState = new DodavanjeVezeState();
        brisanjeState = new BrisanjeState();
        selekcijaState= new SelekcijaState();
        pomeranjeState = new PomeranjeState();
        preuredjivanjeState = new PreuredjivanjeState();
        dupliranjeState = new DupliranjeState();
        zoomState = new ZoomState();
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
    public void setSelekcijaState(){currentState = selekcijaState;}
    public void setPomeranjeState(){currentState = pomeranjeState; }
    public void setPreuredjivanjeState(){currentState = preuredjivanjeState;}
    public void setDupliranjeState(){currentState = dupliranjeState;}
    public void setZoomState(){currentState = zoomState;}

}
