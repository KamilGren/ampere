package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.enums.MaterialType;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CentralHeatingBufferTank that = (CentralHeatingBufferTank) o;
        return getMaterialType() == that.getMaterialType() && Objects.equals(getCapacityL(), that.getCapacityL()) && Objects.equals(getHeightMm(), that.getHeightMm()) && Objects.equals(getDiameterMm(), that.getDiameterMm()) && Objects.equals(getErp(), that.getErp());
    }
}
