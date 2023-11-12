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
public class Package extends ClassyNodeComposite implements IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();
    public Package(String name,ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        System.out.println("Adding child: " + child.getName());
        children.add(child);
        if (child instanceof Diagram) {
            notifySubscribers(new Notification(child, NotificationType.DIAGRAM_ADDED));
        }
    }


    @Override
    public void removeChild(ClassyNode child) {
        System.out.println("Removing child: " + child.getName());
        children.remove(child);
        if (child instanceof Diagram) {
            notifySubscribers(new Notification(child, NotificationType.DIAGRAM_REMOVED));
        }
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        System.out.println("Notifying subscribers of " + notification.getNotificationType() + " for " + this.getName());
        for (ISubscriber subscriber : subscribers) {
            System.out.println("Notifying subscriber: " + subscriber.getClass().getSimpleName());
            subscriber.update(notification);
        }
    }
}
