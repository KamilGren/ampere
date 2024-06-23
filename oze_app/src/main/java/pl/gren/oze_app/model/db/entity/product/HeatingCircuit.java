package pl.gren.oze_app.model.db.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.entity.OrderConfig;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("HeatingCircuit")
@Table(name = "product_heating_circuit")
public class HeatingCircuit extends Product {
    @OneToMany(mappedBy = "heatingCircuit", fetch = FetchType.LAZY)
    private Set<OrderConfig> orderConfigs = new HashSet<>();
}
