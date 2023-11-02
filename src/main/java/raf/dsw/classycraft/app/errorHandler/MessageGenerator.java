package raf.dsw.classycraft.app.errorHandler;

import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();

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

    public void createMessage(String content, MessageType type) {
        Message message = new Message(content, type);
        Notification notification = new Notification(message, NotificationType.MESSAGE);
        notifySubscribers(notification);
    }
}
