package pl.gren.oze_app.model.db.entity.product.quantity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract_quantity_co_buffer")
public class CentralHeatingBufferTankQuantity {
    @EmbeddedId
    private ProductQuantityId id = new ProductQuantityId();

    @ManyToOne
    @MapsId("productId")
    private CentralHeatingBufferTank product;

    @ManyToOne
    @MapsId("contractId")
    private Contract contract;

    private Integer quantity;

    @Override
    public String toString() {
        return "OtherProductQuantity{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    private static AtomicInteger RANDOM_HASH_CODE = new AtomicInteger(1);
    private final int HASH_CODE = RANDOM_HASH_CODE.getAndIncrement();

    @Override
    public int hashCode() {
        return HASH_CODE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentralHeatingBufferTankQuantity that = (CentralHeatingBufferTankQuantity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProduct(), that.getProduct()) && Objects.equals(getContract(), that.getContract()) && Objects.equals(getQuantity(), that.getQuantity());
    }
}
