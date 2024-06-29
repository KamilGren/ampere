package pl.gren.oze_app.model.db.enums;

import pl.gren.oze_app.model.CustomJson;
import pl.gren.oze_app.model.Identity32;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WallType implements CustomJson, Identity32 {
    CERAMIC_BRICK(1, "Ceramic Brick", 0.77),
    CONCRETE_BLOCK(2, "Concrete Block", 0.5),
    CERAMIC_BLOCK(3, "Ceramic Block", 0.5),
    WOODEN_BLOCK(4, "Wooden", 0.16),
    FRAME(5, "Frame", 0.1),
    CONCRETE(6, "Concrete", 1.0),
    AERATED_CONCRETE(7, "Aerated Concrete", 0.17),
    POROTHERM(8, "Porotherm", 0.079),
    YTONG(9, "Ytong", 0.095),
    SILICATE(10, "Silicate", 0.8),
    CLICKER_CONCRETE(11, "Clinker Concrete", 0.56);

    private final int id;
    private final String name;
    private final double scalar;

    private static final List<WallType> TYPES = Arrays.asList(values());
    private static final Map<Integer, WallType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            WallType::getId,
            Function.identity()
    ));

    public static List<WallType> getTypes() {
        return TYPES;
    }

    public static Optional<WallType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    WallType(int id, String name, double scalar) {
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
