package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.enums.MaterialType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("DomesticHotWaterTank")
@Table(name = "product_domestic_hot_water_tank")
public class DomesticHotWaterTank extends Product {

    @Column(name = "material_type_id")
    private MaterialType materialType;

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
}
