package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum VentilationType {
    GRAVITY(1, "Gravity"),
    MECHANICAL(2, "Mechanical"),
    MECHANICAL_WITH_HEAT_RECOVERY(3, "Mechanical with Heat Recovery");

    private final int id;
    private final String name;

    private static final List<VentilationType> TYPES = Arrays.asList(values());
    private static final Map<Integer, VentilationType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            VentilationType::getId,
            Function.identity()
    ));

    public static List<VentilationType> getTypes() {
        return TYPES;
    }

    public static Optional<VentilationType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    VentilationType(int id, String name) {
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
