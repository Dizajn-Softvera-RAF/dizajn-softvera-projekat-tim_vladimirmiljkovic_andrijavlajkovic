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
public class Diagram extends ClassyNodeComposite implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();



    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        children.add(child);
        notifySubscribers(new Notification(child,NotificationType.ADD));
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
        notifySubscribers(new Notification(child,NotificationType.REMOVE));
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
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }


    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new Notification(this, NotificationType.DIAGRAM_NAME_CHANGED));
    }
}
