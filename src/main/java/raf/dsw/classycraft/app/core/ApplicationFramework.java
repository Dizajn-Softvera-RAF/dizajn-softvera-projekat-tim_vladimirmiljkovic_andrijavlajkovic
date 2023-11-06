package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

@Getter
@Setter
public class ApplicationFramework {

    private static ApplicationFramework instance;

    //buduca polja za model celog projekta
    protected ClassyRepository classyRepository;

    private ApplicationFramework(){

    }

    public void initialize(ClassyRepository classyRepository){
        this.classyRepository = classyRepository;
        MainFrame.getInstance().setVisible(true);

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
