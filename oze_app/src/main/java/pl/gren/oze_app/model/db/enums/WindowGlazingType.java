package pl.gren.oze_app.model.db.enums;

import pl.gren.oze_app.model.CustomJson;
import pl.gren.oze_app.model.Identity32;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WindowGlazingType implements CustomJson, Identity32 {
    DOUBLE(1, "Double-Glazed", 1.2),
    TRIPLE(2, "Triple-Glazed", 0.63);

    private final int id;
    private final String name;
    private final double scalar;

    private static final List<WindowGlazingType> TYPES = Arrays.asList(values());
    private static final Map<Integer, WindowGlazingType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            WindowGlazingType::getId,
            Function.identity()
    ));

    public static List<WindowGlazingType> getTypes() {
        return TYPES;
    }

    public static Optional<WindowGlazingType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    WindowGlazingType(int id, String name, double scalar) {
        this.id = id;
        this.name = name;
        this.scalar = scalar;
    }

    public Map<String, Object> toJson() {
        return Map.of(
                "id", id,
                "name", name,
                "scalar", scalar
        );
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
