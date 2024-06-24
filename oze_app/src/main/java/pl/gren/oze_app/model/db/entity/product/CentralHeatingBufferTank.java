package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.enums.MaterialType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("CentralHeatingBufferTank")
@Table(name = "product_central_heating_buffer_tank")
public class CentralHeatingBufferTank extends Product {

    @Column(name = "material_type_id")
    private MaterialType materialType;

    @Column(name = "capacity_l")
    private Integer capacityL;

    @Column(name = "height_mm")
    private Integer heightMm;

    @Column(name = "diameter_mm")
    private Integer diameterMm;

    @Column(name = "erp")
    private String erp;

}
