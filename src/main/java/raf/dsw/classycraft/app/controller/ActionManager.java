package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private NewPackageAction newPackageAction;
    private RemoveProjectAction removeProjectAction;
    private EditProjectAction editProjectAction;
    private EditAuthorAction editAuthorAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction= new AboutUsAction();
        newProjectAction = new NewProjectAction();
        newPackageAction = new NewPackageAction();
        removeProjectAction = new RemoveProjectAction();
        editProjectAction = new EditProjectAction();
        editAuthorAction = new EditAuthorAction();

    }

}
