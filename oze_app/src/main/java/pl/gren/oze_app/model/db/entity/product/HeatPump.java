package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.entity.OrderConfig;
import pl.gren.oze_app.model.db.enums.HeatPumpType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "heating_capacity_negative_20_c")
    private BigDecimal heatingCapacityNegative20C;

    @Column(name = "heating_capacity_negative_15_c")
    private BigDecimal heatingCapacityNegative15C;

    @Column(name = "heating_capacity_negative_7_c")
    private BigDecimal heatingCapacityNegative7C;

    @Column(name = "heating_capacity_negative_2_c")
    private BigDecimal heatingCapacityNegative2C;

    @Column(name = "heating_capacity_positive_2_c")
    private BigDecimal heatingCapacityPositive2C;

    @Column(name = "heating_capacity_positive_7_c")
    private BigDecimal heatingCapacityPositive7C;

    @Column(name = "heating_capacity_positive_12_c")
    private BigDecimal heatingCapacityPositive12C;

    @Column(name = "heating_capacity_positive_15_c")
    private BigDecimal heatingCapacityPositive15C;

    @Column(name = "heating_capacity_positive_20_c")
    private BigDecimal heatingCapacityPositive20C;

    @Column(name = "energy_consumption_negative_20_c")
    private BigDecimal energyConsumptionNegative20C;

    @Column(name = "energy_consumption_negative_15_c")
    private BigDecimal energyConsumptionNegative15C;

    @Column(name = "energy_consumption_negative_7_c")
    private BigDecimal energyConsumptionNegative7C;

    @Column(name = "energy_consumption_negative_2_c")
    private BigDecimal energyConsumptionNegative2C;

    @Column(name = "energy_consumption_positive_2_c")
    private BigDecimal energyConsumptionPositive2C;

    @Column(name = "energy_consumption_positive_7_c")
    private BigDecimal energyConsumptionPositive7C;

    @Column(name = "energy_consumption_positive_12_c")
    private BigDecimal energyConsumptionPositive12C;

    @Column(name = "energy_consumption_positive_15_c")
    private BigDecimal energyConsumptionPositive15C;

    @Column(name = "energy_consumption_positive_20_c")
    private BigDecimal energyConsumptionPositive20C;

    @OneToMany(mappedBy = "heatPump", fetch = FetchType.LAZY)
    private Set<OrderConfig> orderConfigs = new HashSet<>();
}
