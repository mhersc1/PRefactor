package handler.media;

import bean.Trace;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileHandler extends MediaHandler {
    private static FileHandler instance;
    private java.util.logging.FileHandler fileHandler;

    private FileHandler() {
        try {
            Properties connectionProps = loadProperties();
            String fullPath = connectionProps.getProperty("pathDir") + "/" + connectionProps.getProperty("fileName");
            File logFile = new File(fullPath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fileHandler = new java.util.logging.FileHandler(fullPath);
            fileHandler.setFormatter(new SimpleFormatter());

            logger = Logger.getLogger("MyLog");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("It happened an error at configuring file handler");
        }
    }

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
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
