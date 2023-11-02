package raf.dsw.classycraft.app.errorHandler;

import raf.dsw.classycraft.app.observer.Notification;

import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger {
    @Override
    public void update(Notification notification) {
        if (notification.getObjectOfNotification() instanceof Message) {
            Message message = (Message) notification.getObjectOfNotification();
            String formattedMessage = formatMessage(message);
            log(formattedMessage);
        }
    }

    private String formatMessage(Message message) {
        return String.format("[%s][%s] %s",
                message.getType(),
                message.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")),
                message.getContent());
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
