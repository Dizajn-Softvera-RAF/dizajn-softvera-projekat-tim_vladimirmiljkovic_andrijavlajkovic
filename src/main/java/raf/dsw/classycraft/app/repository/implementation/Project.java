package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite implements IPublisher {

    private String author;
    private String resourceFolderPath;

    private List<ISubscriber> subscribers = new ArrayList<>();
    public Project(String name, String author, String resourceFolderPath, ClassyNode parent) {
        super(name, parent);
        this.author = author;
        this.resourceFolderPath = resourceFolderPath;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
            Package paket = (Package) child;
            if (!this.getChildren().contains(paket)){
                this.getChildren().add(paket);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
        notifySubscribers(new Notification(child, NotificationType.PACKAGE_REMOVED));
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new Notification(this,NotificationType.PROJECT_NAME_CHANGED));
    }

}
