package raf.dsw.classycraft.app.errorHandler;

import raf.dsw.classycraft.app.observer.Notification;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    private File logFile;

    public FileLogger() {
        this.logFile = new File("src/main/resources/log/log.txt");
        //System.out.println("Log file path: " + this.logFile.getAbsolutePath());
        clearLogFile();
    }


    @Override
    public void update(Notification notification) {
        if (notification.getObjectOfNotification() instanceof Message) {
            Message message = (Message) notification.getObjectOfNotification();
            String formattedMessage = formatMessage(message);
           // System.out.println("Logging to file: " + formattedMessage);
            log(formattedMessage);
        }
    }

    private String formatMessage(Message message) {
        return String.format("[%s][%s] %s",
                message.getType(),
                message.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")),
                message.getContent());
    }

    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile,true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearLogFile() {
        try {
            new PrintWriter(logFile).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}