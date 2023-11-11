package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.Message;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    //buduca polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private ClassyTree classyTree;
    private PackageView packageView;
    private MainFrame(){

    }

    private void initialize(){

        actionManager = new ActionManager();

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

        classyTree = new ClassyTreeImplementation();
        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JPanel desktop = new JPanel();

        packageView = new PackageView();

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,packageView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

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
        if (notification.getObjectOfNotification() instanceof Package) {
            packageView.update(notification);
        }
    }
}
