import bean.Trace;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;


public class LoggerToDBTest {
    private static final String CONFIG_FILE = "configuration.properties";

    @Before
    public void setUp() throws Exception {
        PropertiesConfiguration configProps = new PropertiesConfiguration(CONFIG_FILE);
        configProps.setProperty("media.profile", "0");
        configProps.setProperty("log.message", "1");
        configProps.setProperty("log.warning", "1");
        configProps.setProperty("log.error", "1");
        configProps.save();

    }

    /**
     * This test currently is creating rows in development database,
     * configure your environment properties
     */
    @Test
    public void logMessage() {
        Trace trace1 = new Trace.Builder()
                .messageText("This is a normal message")
                .type(0)
                .build();
        Trace trace2 = new Trace.Builder()
                .messageText("This is a warning")
                .type(1)
                .build();
        Trace trace3 = new Trace.Builder()
                .messageText("This is an error")
                .type(2)
                .build();

        JobLogger.logMessage(trace1);
        JobLogger.logMessage(trace2);
        JobLogger.logMessage(trace3);
    }

}
