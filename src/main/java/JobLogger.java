

import bean.Trace;
import handler.LoggerHandler;
import handler.media.MediaFactory;

public class JobLogger {

    public static void logMessage(Trace trace) {
        if (trace.getMessageText().isEmpty())
            return;

        LoggerHandler.getInstance().applyFormat(trace);
        MediaFactory.getInstance().printTrace(trace);
    }
}
