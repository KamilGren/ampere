package pl.gren.oze_app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import pl.gren.oze_app.model.db.entity.product.OtherProduct;
import pl.gren.oze_app.model.db.entity.product.quantity.CentralHeatingBufferTankQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.DomesticHotWaterTankQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.HeatPumpQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.OtherProductQuantity;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.CentralHeatingBufferTankQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.DomesticHotWaterTankQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.HeatPumpQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.OtherProductQuantityRepository;

@Service
@AllArgsConstructor
public class ContractService {
    @Autowired private final ContractRepository contractRepository;
    @Autowired private final HeatPumpQuantityRepository heatPumpQtyRepository;
    @Autowired private final OtherProductQuantityRepository otherQtyRepository;
    @Autowired private final DomesticHotWaterTankQuantityRepository cwuQtyRepository;
    @Autowired private final CentralHeatingBufferTankQuantityRepository coQtyRepository;

    public void addHeatPump(Contract contract, HeatPump heatPump) {
        HeatPumpQuantity qty = new HeatPumpQuantity();
        qty.setContract(contract);
        qty.setProduct(heatPump);
        qty.setQuantity(1);
        qty.setId(new ProductQuantityId(heatPump.getId(), contract.getId()));
        heatPumpQtyRepository.save(qty);
    }

    public void addCwuTank(Contract contract, DomesticHotWaterTank cwuTank) {
        var qty = new DomesticHotWaterTankQuantity();
        qty.setContract(contract);
        qty.setProduct(cwuTank);
        qty.setQuantity(1);
        qty.setId(new ProductQuantityId(cwuTank.getId(), contract.getId()));
        cwuQtyRepository.save(qty);
    }

    public void addCoBuffer(Contract contract, CentralHeatingBufferTank coBuffer) {
        var qty = new CentralHeatingBufferTankQuantity();
        qty.setContract(contract);
        qty.setProduct(coBuffer);
        qty.setQuantity(1);
        qty.setId(new ProductQuantityId(coBuffer.getId(), contract.getId()));
        coQtyRepository.save(qty);
    }

    public void addOther(Contract contract, OtherProduct otherProduct) {
        var qty = new OtherProductQuantity();
        qty.setContract(contract);
        qty.setProduct(otherProduct);
        qty.setQuantity(1);
        qty.setId(new ProductQuantityId(otherProduct.getId(), contract.getId()));
        otherQtyRepository.save(qty);
    }

    private ProductQuantityId makeId(Long contractId, Long productId) {
        var qty = new ProductQuantityId();
        qty.setContractId(contractId);
        qty.setProductId(productId);
        return qty;
    }

    public void removeHeatPump(Long contractId, Long productId) {
        heatPumpQtyRepository.deleteById(makeId(contractId, productId));
    }

    public void removeCwu(Long contractId, Long productId) {
        cwuQtyRepository.deleteById(makeId(contractId, productId));
    }

    public void removeCo(Long contractId, Long productId) {
        coQtyRepository.deleteById(makeId(contractId, productId));
    }

    public void removeOther(Long contractId, Long productId) {
        otherQtyRepository.deleteById(makeId(contractId, productId));
    }
}
