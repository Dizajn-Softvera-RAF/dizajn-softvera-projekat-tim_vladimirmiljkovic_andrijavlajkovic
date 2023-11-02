package raf.dsw.classycraft.app.errorHandler;

import raf.dsw.classycraft.app.observer.ISubscriber;

public class LoggerFactory {

    public enum LoggerType {
        CONSOLE, FILE
    }

    public static ISubscriber createLogger(LoggerType type) {
        switch (type) {
            case CONSOLE:
                return new ConsoleLogger();
            case FILE:
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Nevalidan tip loggera: " + type);
        }
    }
}
