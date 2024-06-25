package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.entity.embedded.TemperatureSpecification;
import pl.gren.oze_app.model.db.enums.HeatPumpType;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("HeatPump")
@Table(name = "product_heat_pump")
public class HeatPump extends Product {

    @Column(name = "heat_pump_type_id")
    private HeatPumpType heatPumpType;

    @Column(name = "indoor_unit")
    private String indoorUnit;

    @Column(name = "outdoor_unit")
    private String outdoorUnit;

    @Column(name = "rated_power_kw")
    private BigDecimal ratedPowerKw;

    @Column(name = "heater_power_kw")
    private BigDecimal heaterPowerKw;

    @Column(name = "scop")
    private BigDecimal scop;

    @Column(name = "power_phases")
    private Integer powerPhases;

    @Column(name = "warranty_years")
    private Integer warrantyYears;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="negative20C", column = @Column(name = "heating_capacity_negative_20_c")),
            @AttributeOverride(name="negative15C", column = @Column(name = "heating_capacity_negative_15_c")),
            @AttributeOverride(name="negative7C", column = @Column(name = "heating_capacity_negative_7_c")),
            @AttributeOverride(name="negative2C", column = @Column(name = "heating_capacity_negative_2_c")),
            @AttributeOverride(name="positive2C", column = @Column(name = "heating_capacity_positive_2_c")),
            @AttributeOverride(name="positive7C", column = @Column(name = "heating_capacity_positive_7_c")),
            @AttributeOverride(name="positive12C", column = @Column(name = "heating_capacity_positive_12_c")),
            @AttributeOverride(name="positive15C", column = @Column(name = "heating_capacity_positive_15_c")),
            @AttributeOverride(name="positive20C", column = @Column(name = "heating_capacity_positive_20_c"))
    })
    private TemperatureSpecification heatingCapacity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="negative20C", column = @Column(name = "energy_consumption_negative_20_c")),
            @AttributeOverride(name="negative15C", column = @Column(name = "energy_consumption_negative_15_c")),
            @AttributeOverride(name="negative7C", column = @Column(name = "energy_consumption_negative_7_c")),
            @AttributeOverride(name="negative2C", column = @Column(name = "energy_consumption_negative_2_c")),
            @AttributeOverride(name="positive2C", column = @Column(name = "energy_consumption_positive_2_c")),
            @AttributeOverride(name="positive7C", column = @Column(name = "energy_consumption_positive_7_c")),
            @AttributeOverride(name="positive12C", column = @Column(name = "energy_consumption_positive_12_c")),
            @AttributeOverride(name="positive15C", column = @Column(name = "energy_consumption_positive_15_c")),
            @AttributeOverride(name="positive20C", column = @Column(name = "energy_consumption_positive_20_c"))
    })
    private TemperatureSpecification energyConsumption;

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HeatPump heatPump = (HeatPump) o;
        return getHeatPumpType() == heatPump.getHeatPumpType() && Objects.equals(getIndoorUnit(), heatPump.getIndoorUnit()) && Objects.equals(getOutdoorUnit(), heatPump.getOutdoorUnit()) && Objects.equals(getRatedPowerKw(), heatPump.getRatedPowerKw()) && Objects.equals(getHeaterPowerKw(), heatPump.getHeaterPowerKw()) && Objects.equals(getScop(), heatPump.getScop()) && Objects.equals(getPowerPhases(), heatPump.getPowerPhases()) && Objects.equals(getWarrantyYears(), heatPump.getWarrantyYears()) && Objects.equals(getHeatingCapacity(), heatPump.getHeatingCapacity()) && Objects.equals(getEnergyConsumption(), heatPump.getEnergyConsumption());
    }
}
