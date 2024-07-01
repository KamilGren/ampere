package pl.gren.oze_app.model.db.entity.product.quantity;

import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.Product;

public interface ContractProductQuantity<T extends Product> {
    ProductQuantityId getId();
    T getProduct();
    Contract getContract();
    Integer getQuantity();
    void setId(ProductQuantityId id);
    void setQuantity(Integer quantity);
    void setContract(Contract contract);
    void setProduct(T product);
}
