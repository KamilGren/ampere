package pl.gren.oze_app.model.db.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductQuantityId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "contract_id")
    private Long contractId;

    public ProductQuantityId() {}

    public ProductQuantityId(Long productId, Long contractId) {
        this.productId = productId;
        this.contractId = contractId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "ProductQuantityId{" +
                "productId=" + productId +
                ", contractId=" + contractId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityId that = (ProductQuantityId) o;
        return Objects.equals(getProductId(), that.getProductId()) && Objects.equals(getContractId(), that.getContractId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getContractId());
    }
}
