package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HeatPumpType {
    MONOBLOK(1, "Monoblok"),
    SPLIT(2, "Split"),
    AIO(3, "All-in-one");

    private final int id;
    private final String name;

    private static final List<HeatPumpType> TYPES = Arrays.asList(values());
    private static final Map<Integer, HeatPumpType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            HeatPumpType::getId,
            Function.identity()
    ));

    public static List<HeatPumpType> getTypes() {
        return TYPES;
    }

    public static Optional<HeatPumpType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    HeatPumpType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
