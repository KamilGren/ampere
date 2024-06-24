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
    @Column(name = "other_product_type_id")
    private OtherProductType type;

}
