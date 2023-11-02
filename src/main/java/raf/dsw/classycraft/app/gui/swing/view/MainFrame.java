package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.errorHandler.Message;
import raf.dsw.classycraft.app.errorHandler.MessageGenerator;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    //buduca polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;

    private MessageGenerator messageGenerator;
    private MainFrame(){

    }

    private void initialize(){

        actionManager = new ActionManager();

        messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(this);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);


    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    private int getJOptionPaneMessageType(MessageType messageType) {
        switch (messageType) {
            case ERROR:
                return JOptionPane.ERROR_MESSAGE;
            case WARNING:
                return JOptionPane.WARNING_MESSAGE;
            case INFO:
                return JOptionPane.INFORMATION_MESSAGE;
            default:
                return JOptionPane.PLAIN_MESSAGE;
        }
    }

    public void update(Notification notification) {
        if (notification.getObjectOfNotification() instanceof Message) {
            Message message = (Message) notification.getObjectOfNotification();
            JOptionPane.showMessageDialog(
                    this,
                    message.getContent(),
                    message.getType().toString(),
                    getJOptionPaneMessageType(message.getType())
            );
        }
    }
}
