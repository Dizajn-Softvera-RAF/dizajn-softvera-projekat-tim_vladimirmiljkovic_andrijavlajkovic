package raf.dsw.classycraft.app.observer;

public class Notification {
    private Object objectOfNotification;
    private NotificationType notificationType;

    public Notification(Object objectOfNotification, NotificationType notificationType) {
        this.objectOfNotification = objectOfNotification;
        this.notificationType = notificationType;
    }
}
