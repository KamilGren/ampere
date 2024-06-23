package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WallType {
    CERAMIC_BRICK(1, "ceramicBrick", 0.77),
    CONCRETE_BLOCK(2, "concreteBlock", 0.5),
    CERAMIC_BLOCK(3, "ceramicBlock", 0.5),
    WOODEN_BLOCK(4, "wooden", 0.16),
    FRAME(5, "frame", 0.1),
    CONCRETE(6, "concrete", 1.0),
    AERATED_CONCRETE(7, "aeratedConcrete", 0.17),
    POROTHERM(8, "porotherm", 0.079),
    YTONG(9, "ytong", 0.095),
    SILICATE(10, "silicate", 0.8),
    CLICKER_CONCRETE(11, "clinkerConcrete", 0.56);

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
