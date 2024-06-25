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
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", msrp=" + msrp +
                ", discount=" + discount +
                '}';
    }

    @Override
    public int hashCode() {
        return 9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getModel(), product.getModel()) && Objects.equals(getManufacturer(), product.getManufacturer()) && Objects.equals(getMsrp(), product.getMsrp()) && Objects.equals(getDiscount(), product.getDiscount());
    }
}
