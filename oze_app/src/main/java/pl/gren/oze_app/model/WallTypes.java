package pl.gren.oze_app.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WallTypes {
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

    private int id;
    private String name;
    private double scalar;

    private static List<WallTypes> TYPES = Arrays.asList(values());
    private static Map<Integer, WallTypes> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            WallTypes::getId,
            Function.identity()
    ));

    WallTypes(int id, String name, double scalar) {
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

    public static Optional<WallTypes> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }
}
