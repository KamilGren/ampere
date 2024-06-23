package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WaterUsageType {
    SHOWER(1, "Shower", 50),
    BATH(2, "Bath", 60),
    SHOWER_AND_BATH(3, "Shower and Bath", 70);

    private final int id;
    private final String name;
    private final int scalar;

    private static final List<WaterUsageType> TYPES = Arrays.asList(values());
    private static final Map<Integer, WaterUsageType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            WaterUsageType::getId,
            Function.identity()
    ));

    public static List<WaterUsageType> getTypes() {
        return TYPES;
    }

    public static Optional<WaterUsageType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    WaterUsageType(int id, String name, int scalar) {
        this.id = id;
        this.name = name;
        this.scalar = scalar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScalar() {
        return scalar;
    }
}
