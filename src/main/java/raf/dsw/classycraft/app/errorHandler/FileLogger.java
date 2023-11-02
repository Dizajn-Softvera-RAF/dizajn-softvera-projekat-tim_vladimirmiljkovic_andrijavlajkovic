package raf.dsw.classycraft.app.errorHandler;

import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileLogger implements ISubscriber {

    private File logFile;

    public FileLogger() {
        this.logFile = new File("src/main/resources/log.txt");
    }

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

    private void log(String message) {
        // Append the formatted message to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}