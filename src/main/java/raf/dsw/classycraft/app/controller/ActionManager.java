package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private RemoveProjectAction removeProjectAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction= new AboutUsAction();
        newProjectAction = new NewProjectAction();
        removeProjectAction = new RemoveProjectAction();
    }

}
