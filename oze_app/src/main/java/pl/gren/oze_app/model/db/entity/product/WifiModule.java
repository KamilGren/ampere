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
@DiscriminatorValue("WifiModule")
@Table(name = "product_wifi_module")
public class WifiModule extends Product {
    @OneToMany(mappedBy = "wifiModule", fetch = FetchType.LAZY)
    private Set<OrderConfig> orderConfigs = new HashSet<>();
}
