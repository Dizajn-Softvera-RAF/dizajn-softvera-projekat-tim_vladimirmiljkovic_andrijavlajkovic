package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNode implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
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


}
