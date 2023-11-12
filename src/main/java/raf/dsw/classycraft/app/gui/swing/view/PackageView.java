package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class PackageView extends JPanel implements ISubscriber {
    private JTabbedPane tabbedPane;
    private JLabel authorName;
    private JLabel projectName;
    private Package paket;

    public PackageView() {
        initialise();


    }

    public void initialise(){
        setLayout(new BorderLayout());

        authorName=new JLabel("");
        projectName= new JLabel("");

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1));
        headerPanel.add(projectName);
        headerPanel.add(authorName);

        add(headerPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.setVisible(false);
        authorName.setVisible(false);
        projectName.setVisible(false);
    }

    @Override
    public void update(Notification notification) {
        switch (notification.getNotificationType()) {
            case DIAGRAM_ADDED:
                Diagram addedDiagram = (Diagram) notification.getObjectOfNotification();
                addTabForDiagram(addedDiagram);
                break;
            case DIAGRAM_REMOVED:
                Diagram removedDiagram = (Diagram) notification.getObjectOfNotification();
                removeTabForDiagram(removedDiagram);
                break;
            case PROJECT_NAME_CHANGED:
                updateProjectName((Project) notification.getObjectOfNotification());
                break;
            case DIAGRAM_NAME_CHANGED:
                updateDiagramTabName((Diagram) notification.getObjectOfNotification());
                break;
            case PACKAGE_REMOVED:
                Package removedPackage = (Package) notification.getObjectOfNotification();
                if (removedPackage.equals(this.paket)) {
                    resetView();
                }
                break;
            case PROJECT_REMOVED:
                Project removedProject = (Project) notification.getObjectOfNotification();
                if (this.paket != null && this.paket.getParent().equals(removedProject)) {
                    resetView();
                }
                break;
        }
    }

    public void displayDiagramsForPackage(Package pkg) {
        this.paket = pkg;
        tabbedPane.setVisible(true);
        authorName.setVisible(true);
        projectName.setVisible(true);
        tabbedPane.removeAll();
        for (ClassyNode child : pkg.getChildren()) {
            if (child instanceof Diagram) {
                Diagram diagram = (Diagram) child;
                diagram.addSubscriber(this);
                addTabForDiagram((Diagram) child);
            }
        }
    }

    private void addTabForDiagram(Diagram diagram) {
        DiagramView view = new DiagramView(diagram);
        tabbedPane.addTab(diagram.getName(), view);
    }
    public void subscribeToPackage(Package pkg) {
        if (this.paket != null) {
            this.paket.removeSubscriber(this); // Ukloniti pretplatu sa starog paketa
        }
        this.paket = pkg;
        pkg.addSubscriber(this); // Pretplatiti se na novi paket
        displayDiagramsForPackage(pkg); // Prikazati dijagrame za novi paket

        // Pronalazak najblizeg Projecta u stablu
        ClassyNode current = pkg;
        while (current != null && !(current instanceof Project)) {
            current = current.getParent();
        }

        if (current instanceof Project) {
            Project project = (Project) current;
            project.addSubscriber(this);
            projectName.setText(project.getName());
            authorName.setText(project.getAuthor());
        }
    }

    private void removeTabForDiagram(Diagram diagram) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component component = tabbedPane.getComponentAt(i);
            if (component instanceof DiagramView) {
                DiagramView view = (DiagramView) component;
                if (view.getDiagram().equals(diagram)) {
                    tabbedPane.remove(i);
                    break;
                }
            }
        }
    }
    public void resetView() {
        // Sakrij ili resetuj PackageView
        authorName.setVisible(false);
        projectName.setVisible(false);
        tabbedPane.setVisible(false);

    }
    private void updateDiagramTabName(Diagram diagram) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component comp = tabbedPane.getComponentAt(i);
            if (comp instanceof DiagramView) {
                DiagramView view = (DiagramView) comp;
                if (view.getDiagram().equals(diagram)) {
                    tabbedPane.setTitleAt(i, diagram.getName());
                    break;
                }
            }
        }
    }

    private void updateProjectName(Project project) {
        if (paket != null && paket.getParent() == project) {
            projectName.setText(project.getName());
        }
    }

}
