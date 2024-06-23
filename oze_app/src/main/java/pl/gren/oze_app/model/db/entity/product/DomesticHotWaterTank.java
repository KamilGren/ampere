package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.entity.OrderConfig;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("DomesticHotWaterTank")
@Table(name = "product_domestic_hot_water_tank")
public class DomesticHotWaterTank extends Product {

    @Column(name = "material_type_id")
    private Integer materialType;

    @Column(name = "capacity_l")
    private Integer capacityL;

    @Column(name = "height_mm")
    private Integer heightMm;

    @Column(name = "diameter_mm")
    private Integer diameterMm;

    @Column(name = "coil")
    private String coil;

    @Column(name = "heater")
    private BigDecimal heater;

    @Column(name = "erp")
    private String erp;

    @OneToMany(mappedBy = "cwuTank", fetch = FetchType.LAZY)
    private Set<OrderConfig> orderConfigs = new HashSet<>();
}
