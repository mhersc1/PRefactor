package handler.media;

import enums.MediaEnum;
import util.Util;

import java.util.Optional;
import java.util.Properties;

public class MediaFactory {
    private static final String CONFIG_FILE = "configuration.properties";

    public static MediaHandler getInstance() {
        MediaHandler mediaHandler = null;
        Properties envProps = Util.loadProperties(CONFIG_FILE);
        String profile = envProps.getProperty("media.profile");
        Optional<MediaEnum> mediaOptional = MediaEnum.getEnumByType(Integer.parseInt(profile));

        if (mediaOptional.isPresent()) {
            switch (mediaOptional.get()) {
                case DB:
                    mediaHandler = DbHandler.getInstance();
                    break;
                case FILE:
                    mediaHandler = FileHandler.getInstance();
                    break;
                case CONSOLE:
                    mediaHandler = ConsoleHandler.getInstance();
                    break;
            }
            return mediaHandler;
        } else
            throw new RuntimeException("Media not found, invalid configuration.");

    }
}
