package handler;

import bean.Trace;
import enums.TraceEnum;
import util.Util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class LoggerHandler {
    private static final String CONFIG_FILE = "configuration.properties";
    private static final String KEYWORD = "log.";
    private static LoggerHandler instance;
    private List<TraceEnum> activeTraces;

    private LoggerHandler() {
        setActiveTraces();
    }

    private List<TraceEnum> setActiveTraces() {
        activeTraces = new ArrayList<>();
        Properties properties = Util.loadProperties(CONFIG_FILE);
        properties.forEach((key, value) -> {
            if (((String) key).startsWith(KEYWORD) && value.equals("1")) {
                String type = ((String) key).replace(KEYWORD, "");
                TraceEnum.getEnumByValue(type).ifPresent(activeTraces::add);
            }
        });

        if (activeTraces.size() == 0)
            throw new RuntimeException("Error or Warning or Message configuration must be specified");
        return activeTraces;
    }

    public static LoggerHandler getInstance() {
        if (instance == null)
            instance = new LoggerHandler();
        return instance;
    }

    public void applyFormat(Trace trace) {
        activeTraces.forEach(tr -> {
            if (tr.getType().equals(trace.getType())) {
                String message = "";
                boolean valid = true;
                switch (tr) {
                    case MESSAGE:
                        message = formatMessage(trace.getMessageText());
                        break;
                    case WARNING:
                        message = formatWarningMessage(trace.getMessageText());
                        break;
                    case ERROR:
                        message = formatErrorMessage(trace.getMessageText());
                        break;
                    default:
                        valid = false;
                }

                trace.changeMessageTxt(message);
                trace.changeState(valid);
            }
        });
    }

    private static String formatErrorMessage(String messageText) {
        return "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
    }

    private static String formatWarningMessage(String messageText) {
        return "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
    }

    private static String formatMessage(String messageText) {
        return "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
    }
}
