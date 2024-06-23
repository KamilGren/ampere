package pl.gren.oze_app.model.db.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BasementFloorData {
    WITH_2(2, true, 0.86, 0.58, 0.44, 0.28, 0.16),
    WITH_4(4, true, 0.64, 0.48, 0.38, 0.26, 0.16),
    WITH_6(6, true, 0.52, 0.4, 0.33, 0.25, 0.15),
    WITH_8(8, true, 0.44, 0.35, 0.29, 0.23, 0.15),
    WITH_10(10, true, 0.38, 0.31, 0.26, 0.21, 0.14),
    WITH_12(12, true, 0.34, 0.28, 0.24, 0.19, 0.14),
    WITH_14(14, true, 0.3, 0.25, 0.22, 0.18, 0.13),
    WITH_16(16, true, 0.28, 0.23, 0.2, 0.17, 0.12),
    WITH_18(18, true, 0.25, 0.22, 0.19, 0.16, 0.12),
    WITH_20(20, true, 0.24, 0.2, 0.18, 0.15, 0.11),
    WITHOUT_2(2, false, 1.3, 0.77, 0.55, 0.33, 0.17),
    WITHOUT_4(4, false, 0.88, 0.59, 0.45, 0.3, 0.17),
    WITHOUT_6(6, false, 0.68, 0.48, 0.38, 0.27, 0.17),
    WITHOUT_8(8, false, 0.55, 0.41, 0.33, 0.25, 0.16),
    WITHOUT_10(10, false, 0.47, 0.36, 0.3, 0.23, 0.15),
    WITHOUT_12(12, false, 0.41, 0.32, 0.27, 0.21, 0.14),
    WITHOUT_14(14, false, 0.37, 0.29, 0.24, 0.19, 0.14),
    WITHOUT_16(16, false, 0.33, 0.26, 0.22, 0.18, 0.13),
    WITHOUT_18(18, false, 0.31, 0.24, 0.21, 0.17, 0.12),
    WITHOUT_20(20, false, 0.28, 0.22, 0.19, 0.16, 0.12);

    private final int b;
    private final boolean insulated;
    private final double withoutInsulation;
    private final double at2;
    private final double at1;
    private final double atHalf;
    private final double atQuarter;

    BasementFloorData(int b, boolean insulated, double withoutInsulation, double at2, double at1, double atHalf, double atQuarter) {
        this.b = b;
        this.insulated = insulated;
        this.withoutInsulation = withoutInsulation;
        this.at2 = at2;
        this.at1 = at1;
        this.atHalf = atHalf;
        this.atQuarter = atQuarter;
    }

    private static List<BasementFloorData> TYPES = Arrays.asList(values());
    private static List<BasementFloorData> WITH_TYPES = TYPES.stream().filter(BasementFloorData::isInsulated).toList();
    private static List<BasementFloorData> WITHOUT_TYPES = TYPES.stream().filter(x -> !x.isInsulated()).toList();

    private static Map<Integer, BasementFloorData> WITH_ID_MAP = WITH_TYPES.stream().collect(Collectors.toMap(
            BasementFloorData::getB,
            Function.identity()
    ));
    private static Map<Integer, BasementFloorData> WITHOUT_ID_MAP = WITHOUT_TYPES.stream().collect(Collectors.toMap(
            BasementFloorData::getB,
            Function.identity()
    ));

    public static List<BasementFloorData> getTypes() {
        return TYPES;
    }

    public static List<BasementFloorData> getTypes(boolean isInsulated) {
        if (isInsulated) {
            return WITH_TYPES;
        } else {
            return WITHOUT_TYPES;
        }
    }

    public static List<BasementFloorData> getInsulated(boolean isInsulated) {
        return WITH_TYPES;
    }

    public static List<BasementFloorData> getUninsulated(boolean isInsulated) {
        return WITHOUT_TYPES;
    }

    public static Optional<BasementFloorData> findByIdAndInsulated(int id, boolean insulated) {
        if (insulated) {
            return Optional.ofNullable(WITH_ID_MAP.get(id));
        } else {
            return Optional.ofNullable(WITHOUT_ID_MAP.get(id));
        }
    }

    public int getB() {
        return b;
    }

    public boolean isInsulated() {
        return insulated;
    }

    public double getWithoutInsulation() {
        return withoutInsulation;
    }

    public double getAt2() {
        return at2;
    }

    public double getAt1() {
        return at1;
    }

    public double getAtHalf() {
        return atHalf;
    }

    public double getAtQuarter() {
        return atQuarter;
    }
}
