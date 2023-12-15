package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.ElementCounters;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PackageView extends JPanel implements ISubscriber {
    private JTabbedPane tabbedPane;
    private JLabel authorName;
    private JLabel projectName;
    private Package paket;
    private StateManager stateManager;
    private ClassyBar classyBar;
    private Map<Diagram, ElementCounters> countersMap = new HashMap<>();
    public PackageView() {
        initialise();
    }

    public void initialise(){
        setLayout(new BorderLayout());
        this.stateManager = new StateManager();
        authorName=new JLabel("");
        projectName= new JLabel("");

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1));
        headerPanel.add(projectName);
        headerPanel.add(authorName);

        add(headerPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        classyBar = new ClassyBar(this);
        add(classyBar, BorderLayout.EAST);
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
        DiagramView view = new DiagramView(diagram, this);
        diagram.addSubscriber(view);
        view.setPreferredSize(new Dimension(5000,5000));
        JScrollPane scrollPane = new JScrollPane(view);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        AdjustmentListener repaintOnScroll = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                view.setScrollPosition(scrollPane.getHorizontalScrollBar().getValue(), scrollPane.getVerticalScrollBar().getValue());
                view.repaint();
            }
        };

        scrollPane.getHorizontalScrollBar().addAdjustmentListener(repaintOnScroll);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(repaintOnScroll);

        tabbedPane.addTab(diagram.getName(), scrollPane);
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
            if (component instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) component;
                Component viewComponent = scrollPane.getViewport().getView();
                if (viewComponent instanceof DiagramView) {
                    DiagramView view = (DiagramView) viewComponent;
                    if (view.getDiagram().equals(diagram)) {
                        diagram.removeSubscriber(view);
                        tabbedPane.remove(i);
                        break;
                    }
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
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                Component viewComponent = scrollPane.getViewport().getView();
                if (viewComponent instanceof DiagramView) {
                    DiagramView view = (DiagramView) viewComponent;
                    if (view.getDiagram().equals(diagram)) {
                        tabbedPane.setTitleAt(i, diagram.getName());
                        break;
                    }
                }
            }
        }
    }

    private void updateProjectName(Project project) {
        if (paket != null && paket.getParent() == project) {
            projectName.setText(project.getName());
        }
    }

    public void startDodavanjeICOState(){
        this.stateManager.setDodavanjeICOState();
    }
    public void startDodavanjeVezeState(){
        this.stateManager.setDodavanjeVezeState();
    }
    public void startBrisanjeState(){
        this.stateManager.setBrisanjeState();
    }
    public void startSelekcijaState(){
        this.stateManager.setSelekcijaState();
    }
    public void startPomeranjeState(){
        this.stateManager.setPomeranjeState();
    }
    public void startPreuredjivanjeState(){
        this.stateManager.setPreuredjivanjeState();
    }
    public void startDupliranjeState(){
        this.stateManager.setDupliranjeState();
    }
    public void startZoomState(){
        this.stateManager.setZoomState();
    }

    public void misKliknut(int x, int y, DiagramView diagramView) {
        this.getStateManager().getCurrent().misKliknut(x,y,diagramView);
    }
    public void misPovucen(int x, int y, DiagramView diagramView) {
        this.getStateManager().getCurrent().misPovucen(x, y, diagramView);
    }

    public void misOtpusten(int x, int y, DiagramView diagramView) {
        this.getStateManager().getCurrent().misOtpusten(x, y, diagramView);
    }



    public String generateElementName(Diagram diagram, String type) {
        ElementCounters counters = countersMap.computeIfAbsent(diagram, k -> new ElementCounters());

        switch (type) {
            case "Klasa": return "Klasa" + counters.getNextKlasaCount();
            case "Enum": return "Enum" + counters.getNextEnuumCount();
            case "Interfejs": return "Interfejs" + counters.getNextInterfejsCount();
            case "Agregacija": return "Agregacija" + counters.getNextAgregacijaCount();
            case "Kompozicija": return "Kompozicija" + counters.getNextKompozicijaCount();
            case "Generalizacija": return "Generalizacija" + counters.getNextGeneralizacijaCount();
            case "Zavisnost": return "Zavisnost" + counters.getNextZavisnostCount();
            case "KlasaKopija": return "_copy" + counters.getNextKlasaCopyCount();
            case "InterfejsKopija": return "_copy" + counters.getNextInterfejsCopyCount();
            case "EnuumKopija": return "_copy" + counters.getNextEnuumCopyCount();
            default: return "Element";
        }
    }
}
