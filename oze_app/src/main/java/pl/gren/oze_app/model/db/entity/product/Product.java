package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "subclass")
@Table(name = "product")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "msrp")
    private BigDecimal msrp;

    @Column(name = "discount")
    private BigDecimal discount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Product p) {
            return Objects.equals(this.id, p.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getClass());
    }
}
