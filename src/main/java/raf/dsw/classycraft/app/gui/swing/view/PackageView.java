package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

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
        tabbedPane = new JTabbedPane();
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.setVisible(false);
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
        }
    }

    public void displayDiagramsForPackage(Package pkg) {
        this.paket = pkg;
        tabbedPane.setVisible(true);
        tabbedPane.removeAll();
        for (ClassyNode child : pkg.getChildren()) {
            if (child instanceof Diagram) {
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
        tabbedPane.setVisible(false);
    }

}
