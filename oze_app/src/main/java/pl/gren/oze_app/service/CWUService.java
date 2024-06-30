package pl.gren.oze_app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;
import pl.gren.oze_app.model.db.repository.product.DomesticHotWaterTankRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CWUService {

    @Autowired private final DomesticHotWaterTankRepository repository;

    public List<DomesticHotWaterTank> findAll() {
        return repository.findAll();
    }

    public List<DomesticHotWaterTank> filterByManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findAllByManufacturer(manufacturer);
        }
    }

    public Set<String> findDistinctManufacturers() {
        return new HashSet<>(repository.findAllManufacturers());
    }

}
