package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    //buduca polja za model celog projekta
    protected ClassyRepository classyRepository;

    private ApplicationFramework(){

    }

    public void initialize(ClassyRepository classyRepository){
        MainFrame.getInstance().setVisible(true);
        this.classyRepository = classyRepository;
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
