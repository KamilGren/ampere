package pl.gren.oze_app.model.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.enums.*;

import java.util.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building_info")
public class BuildingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "location")
    private String location;

    @Column(name = "location_temperature_c")
    private BigDecimal locationTemperatureCelcius;

    @Column(name = "building_type_id")
    private BuildingType buildingType;

    @Column(name = "length_m")
    private BigDecimal lengthMeters;

    @Column(name = "width_m")
    private BigDecimal widthMeters;

    @Column(name = "heated_area_m2")
    private BigDecimal heatedAreaSquareMeters;

    @Column(name = "ceiling_height_m")
    private BigDecimal ceilingHeightMeters;

    @Column(name = "heating_temperature_c")
    private BigDecimal heatingTemperatureCelcius;

    @Column(name = "cooling_temperature_c")
    private BigDecimal coolingTemperatureCelcius;

    @Column(name = "has_basement")
    private Boolean hasBasement;

    @Column(name = "wall_type_id")
    private WallType wallType;

    @Column(name = "wall_insulation_type_id")
    private InsulationType wallInsulationType;

    @Column(name = "roof_insulation_type_id")
    private InsulationType roofInsulationType;

    @Column(name = "floor_insulation_type_id")
    private InsulationType floorInsulationType;

    @Column(name = "wall_thickness_cm")
    private BigDecimal wallThicknessCm;

    @Column(name = "wall_insulation_thickness_cm")
    private BigDecimal wallInsulationThicknessCm;

    @Column(name = "roof_insulation_thickness_cm")
    private BigDecimal roofInsulationThicknessCm;

    @Column(name = "floor_insulation_thickness_cm")
    private BigDecimal floorInsulationThicknessCm;

    @Column(name = "window_glazing_type_id")
    private WindowGlazingType windowGlazingType;

    @Column(name = "window_count")
    private Integer windowCount;

    @Column(name = "exterior_door_count")
    private Integer exteriorDoorCount;

    @Column(name = "ventilation_type_id")
    private VentilationType ventilationType;

    @Column(name = "fuel_type_id")
    private FuelType fuelType;

    @Column(name = "fuel_usage_amount")
    private BigDecimal fuelUsageAmount;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "water_usage_type_id")
    private WaterUsageType waterUsageType;

    @OneToMany(mappedBy = "buildingInfo", fetch = FetchType.LAZY)
    private Set<Contract> contract = new HashSet<>();

    @Override
    public String toString() {
        return "BuildingInfo{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", locationTemperatureCelcius=" + locationTemperatureCelcius +
                ", buildingType=" + buildingType +
                ", lengthMeters=" + lengthMeters +
                ", widthMeters=" + widthMeters +
                ", heatedAreaSquareMeters=" + heatedAreaSquareMeters +
                ", ceilingHeightMeters=" + ceilingHeightMeters +
                ", heatingTemperatureCelcius=" + heatingTemperatureCelcius +
                ", coolingTemperatureCelcius=" + coolingTemperatureCelcius +
                ", hasBasement=" + hasBasement +
                ", wallType=" + wallType +
                ", wallInsulationType=" + wallInsulationType +
                ", roofInsulationType=" + roofInsulationType +
                ", floorInsulationType=" + floorInsulationType +
                ", wallThicknessCm=" + wallThicknessCm +
                ", wallInsulationThicknessCm=" + wallInsulationThicknessCm +
                ", roofInsulationThicknessCm=" + roofInsulationThicknessCm +
                ", floorInsulationThicknessCm=" + floorInsulationThicknessCm +
                ", windowGlazingType=" + windowGlazingType +
                ", windowCount=" + windowCount +
                ", exteriorDoorCount=" + exteriorDoorCount +
                ", ventilationType=" + ventilationType +
                ", fuelType=" + fuelType +
                ", fuelUsageAmount=" + fuelUsageAmount +
                ", peopleCount=" + peopleCount +
                ", waterUsageType=" + waterUsageType +
                '}';
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingInfo that = (BuildingInfo) o;
        return Objects.equals(this.id, that.id);
    }
}
