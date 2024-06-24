package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OtherProductType {
    CICULATION(1, "Circulation"),
    HEAT_PUMP_BASE(2, "Heat Pump Base"),
    HEATING_CIRCUIT(3, "Heating Circuit"),
    SEPARATOR(4, "Separator"),
    WIFI_MODULE(5, "Wi-Fi Module");

    private final int id;
    private final String name;

    OtherProductType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static final List<OtherProductType> TYPES = Arrays.asList(values());
    private static final Map<Integer, OtherProductType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            OtherProductType::getId,
            Function.identity()
    ));

    public static List<OtherProductType> getTypes() {
        return TYPES;
    }

    public static Optional<OtherProductType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
