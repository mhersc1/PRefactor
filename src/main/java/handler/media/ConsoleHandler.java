package handler.media;

import bean.Trace;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleHandler extends MediaHandler {
    private static ConsoleHandler instance;
    private java.util.logging.ConsoleHandler consoleHandler;

    private ConsoleHandler() {
        logger = Logger.getLogger("MyLog");
        consoleHandler = new java.util.logging.ConsoleHandler();
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);//In order to not get duplicate logs
    }

    public static ConsoleHandler getInstance() {
        if (instance == null) {
            instance = new ConsoleHandler();
        }
        return instance;
    }

    @Override
    public void printTrace(Trace trace) {
        if (!trace.isValid())
            return;

        logger.log(Level.INFO, trace.getMessageText());
    }
}
