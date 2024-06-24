package pl.gren.oze_app.model.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import pl.gren.oze_app.model.db.entity.product.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_config")
public class OrderConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "heat_pump_id")
    private HeatPump heatPump;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cwu_tank_id")
    private DomesticHotWaterTank cwuTank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "co_buffer_id")
    private CentralHeatingBufferTank coBuffer;

    @OneToOne(mappedBy = "orderConfig", fetch = FetchType.LAZY)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderConfig that = (OrderConfig) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getClass());
    }
}