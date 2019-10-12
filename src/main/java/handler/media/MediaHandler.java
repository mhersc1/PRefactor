package handler.media;

import bean.Trace;
import util.Util;

import java.util.Properties;
import java.util.logging.Logger;

public abstract class MediaHandler {
    private static final String CONFIG_FILE = "environment.properties";
    static Logger logger;

    abstract public void printTrace(Trace trace);

    protected Properties loadProperties(){
        return Util.loadProperties(CONFIG_FILE);
    }
}
