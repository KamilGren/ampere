package pl.gren.oze_app.model.db.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gren.oze_app.model.db.enums.OtherProductType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("OtherProduct")
@Table(name = "product_other")
public class OtherProduct extends Product {
    @Column(name = "type_id")
    private OtherProductType type;

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OtherProduct that = (OtherProduct) o;
        return getType() == that.getType();
    }
}
