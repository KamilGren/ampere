package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum InsulationType {
    STYROFOAM(1, "Styrofoam", 0.031),
    GRAPHITE_STYROFOAM(2, "Graphite Styrofoam", 0.029),
    XPS(3, "XPS", 0.045),
    MINERAL_WOOL(4, "Mineral Wool", 0.035),
    PURIFIED_FOAM(5, "Purified Foam", 0.023);

    private final int id;
    private final String name;
    private final double scalar;

    private static final List<InsulationType> TYPES = Arrays.asList(values());
    private static final Map<Integer, InsulationType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            InsulationType::getId,
            Function.identity()
    ));

    public static List<InsulationType> getTypes() {
        return TYPES;
    }

    public static Optional<InsulationType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    InsulationType(int id, String name, double scalar) {
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

    public double getScalar() {
        return scalar;
    }
}
