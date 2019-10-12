package enums;

import java.util.Arrays;
import java.util.Optional;

public enum TraceEnum {
    MESSAGE(0),
    WARNING(1),
    ERROR(2);

    private Integer type;

    TraceEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static Optional<TraceEnum> getEnumByValue(String value) {
        return Arrays.stream(values()).filter(traceEnum -> traceEnum.name().equalsIgnoreCase(value)).findFirst();
    }
}
