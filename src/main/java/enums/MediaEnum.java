package enums;

import java.util.Arrays;
import java.util.Optional;

public enum MediaEnum {

    DB(0),
    CONSOLE(1),
    FILE(2);

    private final Integer type;

    MediaEnum(Integer type) {
        this.type = type;
    }

    public static Optional<MediaEnum> getEnumByType(Integer type) {
        return Arrays.stream(values()).filter(media -> media.type.equals(type)).findFirst();
    }

    public Integer getType() {
        return type;
    }
}
