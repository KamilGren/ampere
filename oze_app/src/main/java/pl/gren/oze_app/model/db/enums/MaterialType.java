package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MaterialType {
    ENAMELED(1, "Enameled"), // EMALIOWANY
    STAINLESS_STEEL(2, "INOX (Stainless Steel)"),
    STEEL(3, "Steel"); // STAL

    private final int id;
    private final String name;

    private static final List<MaterialType> TYPES = Arrays.asList(values());
    private static final Map<Integer, MaterialType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            MaterialType::getId,
            Function.identity()
    ));

    public static List<MaterialType> getTypes() {
        return TYPES;
    }

    public static Optional<MaterialType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    MaterialType(int id, String name) {
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
