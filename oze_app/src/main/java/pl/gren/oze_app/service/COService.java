package pl.gren.oze_app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;
import pl.gren.oze_app.model.db.repository.product.CentralHeatingBufferTankRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class COService {
    @Autowired private final CentralHeatingBufferTankRepository repository;

    public List<CentralHeatingBufferTank> findAll() {
        return repository.findAll();
    }

    public List<CentralHeatingBufferTank> filterByManufacturer(String manufacturer) {
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
