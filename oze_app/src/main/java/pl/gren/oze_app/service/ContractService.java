package pl.gren.oze_app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.*;
import pl.gren.oze_app.model.db.entity.product.quantity.*;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.CentralHeatingBufferTankQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.DomesticHotWaterTankQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.HeatPumpQuantityRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.OtherProductQuantityRepository;
import pl.gren.oze_app.model.dto.contracts.QuantityUpdateDTO;

@Service
@AllArgsConstructor
public class ContractService {
    @Autowired private final ContractRepository contractRepository;
    @Autowired private final HeatPumpQuantityRepository heatPumpQtyRepository;
    @Autowired private final OtherProductQuantityRepository otherQtyRepository;
    @Autowired private final DomesticHotWaterTankQuantityRepository cwuQtyRepository;
    @Autowired private final CentralHeatingBufferTankQuantityRepository coQtyRepository;

    public void addHeatPump(Contract contract, HeatPump heatPump) {
        addNew(heatPumpQtyRepository, heatPump, new HeatPumpQuantity(), contract);
    }

    public void addCwuTank(Contract contract, DomesticHotWaterTank cwuTank) {
        addNew(cwuQtyRepository, cwuTank, new DomesticHotWaterTankQuantity(), contract);
    }

    public void addCoBuffer(Contract contract, CentralHeatingBufferTank coBuffer) {
        addNew(coQtyRepository, coBuffer, new CentralHeatingBufferTankQuantity(), contract);
    }

    public void addOther(Contract contract, OtherProduct otherProduct) {
        addNew(otherQtyRepository, otherProduct, new OtherProductQuantity(), contract);
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

    public void updateHeatPumpQty(QuantityUpdateDTO dto) {
        updateQty(heatPumpQtyRepository, dto);
    }

    public void updateCwuQty(QuantityUpdateDTO dto) {
        updateQty(cwuQtyRepository, dto);
    }

    public void updateCoQty(QuantityUpdateDTO dto) {
        updateQty(coQtyRepository, dto);
    }

    public void updateOtherQty(QuantityUpdateDTO dto) {
        updateQty(otherQtyRepository, dto);
    }

    private <QTY extends ContractProductQuantity<?>, T extends CrudRepository<QTY, ProductQuantityId>> void updateQty(T repository, QuantityUpdateDTO dto) {
        var result = repository.findById(makeId(dto.getContractId(), dto.getProductId())).orElseThrow();
        result.setQuantity(dto.getQuantity());
        repository.save(result);
    }

    private <
            V extends Product,
            QTY extends ContractProductQuantity<V>,
            T extends CrudRepository<QTY, ProductQuantityId>
    > void addNew(T repository, V product, QTY qty, Contract contract) {
        qty.setContract(contract);
        qty.setProduct(product);
        qty.setQuantity(1);
        qty.setId(new ProductQuantityId(product.getId(), contract.getId()));
        repository.save(qty);
    }

}
