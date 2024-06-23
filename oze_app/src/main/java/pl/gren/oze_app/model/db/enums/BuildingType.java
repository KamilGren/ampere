package pl.gren.oze_app.model.db.enums;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BuildingType {
    SINGLE_STORY(1, "Single Story", 1.0, 1.1),
    SINGLE_STORY_WITH_USABLE_ATTIC(2, "Single Story (Usable Attic)", 1.2, 1.5),
    SINGLE_STORY_WITH_UNUSABLE_ATTIC(3, "Single Story (Unusable Attic)", 1.2, 1.1),
    TWO_STORY(4, "Two Story", 2.0, 1.1),
    TWO_STORY_WITH_USABLE_ATTIC(5, "Two Story (Usable Attic)", 2.5, 1.5);

    private final int id;
    private final String name;
    private final double scalar1;
    private final double scalar2;

    private static List<BuildingType> TYPES = Arrays.asList(values());
    private static Map<Integer, BuildingType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            BuildingType::getId,
            Function.identity()
    ));

    public static List<BuildingType> getTypes() {
        return TYPES;
    }

    public static Optional<BuildingType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    BuildingType(int id, String name, double scalar1, double scalar2) {
        this.id = id;
        this.name = name;
        this.scalar1 = scalar1;
        this.scalar2 = scalar2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScalar1() {
        return scalar1;
    }

    public double getScalar2() {
        return scalar2;
    }

}

