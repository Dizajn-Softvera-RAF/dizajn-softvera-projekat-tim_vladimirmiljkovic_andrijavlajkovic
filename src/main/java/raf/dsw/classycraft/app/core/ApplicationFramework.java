package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.errorHandler.Logger;
import raf.dsw.classycraft.app.errorHandler.LoggerFactory;
import raf.dsw.classycraft.app.errorHandler.MessageGenerator;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

@Getter
@Setter
public class ApplicationFramework {

    private static ApplicationFramework instance;

    //buduca polja za model celog projekta
    protected ClassyRepository classyRepository;

    MessageGenerator messageGenerator;

    Logger consoleLogger;

    Logger fileLogger;

    private ApplicationFramework(){

    }

    public void initialize(ClassyRepository classyRepository){
        this.classyRepository = classyRepository;
        // Inicijalizacija MessageGenerator-a
        this.messageGenerator = new MessageGenerator();

        // Kreiranje i pretplata logera
        this.consoleLogger = LoggerFactory.createLogger(LoggerFactory.LoggerType.CONSOLE);
        this.fileLogger = LoggerFactory.createLogger(LoggerFactory.LoggerType.FILE);

        this.messageGenerator.addSubscriber(consoleLogger);
        this.messageGenerator.addSubscriber(fileLogger);
        this.messageGenerator.addSubscriber(MainFrame.getInstance());

        MainFrame.getInstance().setVisible(true);

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
