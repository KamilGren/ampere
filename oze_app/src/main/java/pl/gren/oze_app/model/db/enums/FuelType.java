package pl.gren.oze_app.model.db.enums;

import pl.gren.oze_app.model.CustomJson;
import pl.gren.oze_app.model.Identity32;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FuelType implements CustomJson, Identity32 {
    NATURAL_GAS(1, "Natural Gas", "m^3", 95, 10d),
    LPG(2, "LPG (liquefied petroleum gas)", "L", 95, 6.85),
    FUEL_OIL(3, "Fuel Oil", "L", 90, 10),
    PELLET(4, "Pellet", "kg", 85, 4.9),
    ECO_PEA_COAL(5, "Eco-pea Coal", "kg", 75, 6.4),
    HARD_COAL(6, "Hard Coal", "kg", 5, 7.2);

    private final int id;
    private final String name;
    private final String unit;
    private final int efficiencyPercentage;
    private final double energyAmountKwh;

    private static List<FuelType> TYPES = Arrays.asList(values());
    private static Map<Integer, FuelType> ID_MAP = TYPES.stream().collect(Collectors.toMap(
            FuelType::getId,
            Function.identity()
    ));

    public static List<FuelType> getTypes() {
        return TYPES;
    }

    public static Optional<FuelType> findById(int id) {
        return Optional.ofNullable(ID_MAP.get(id));
    }

    FuelType(int id, String name, String unit, int efficiencyPercentage, double energyAmountKwh) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.efficiencyPercentage = efficiencyPercentage;
        this.energyAmountKwh = energyAmountKwh;
    }

    public Map<String, Object> toJson() {
        return Map.of(
                "id", id,
                "name", name,
                "unit", unit,
                "efficiencyPercentage", efficiencyPercentage,
                "energyAmountKwh", energyAmountKwh
        );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getEfficiencyPercentage() {
        return efficiencyPercentage;
    }

    public double getEnergyAmountKwh() {
        return energyAmountKwh;
    }
}
