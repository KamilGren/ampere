package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.exceptions.BuildingRequirementsNotFoundException;
import pl.gren.oze_app.model.BuildingRequirements;
import pl.gren.oze_app.oldrepository.BuildingRequirementsRepository;

import java.util.List;

@Service
public class BuildingRequirementsService {

    private final BuildingRequirementsRepository buildingRequrirementsRepository;

    @Autowired
    public BuildingRequirementsService(BuildingRequirementsRepository buildingRequirementsRepository) {
        this.buildingRequrirementsRepository = buildingRequirementsRepository;
    }

    public List<BuildingRequirements> getAllBuildingRequirements() {
        return buildingRequrirementsRepository.findAll();
    }

    public BuildingRequirements getBuildingReqById(Long id) {

        return buildingRequrirementsRepository.findById(id).orElseThrow(() -> new BuildingRequirementsNotFoundException("Brak danych budynku o numerze id: " + id));
    }

    public BuildingRequirements getBuildingReqByLocation(String location) {

        return buildingRequrirementsRepository.findByLocation(location).orElseThrow(() -> new BuildingRequirementsNotFoundException("Brak danych budynku pochodzących z lokacji: " + location));
    }

    public BuildingRequirements saveBuildingRequirements(BuildingRequirements buildingRequirements) {
        return buildingRequrirementsRepository.save(buildingRequirements);
    }

    public void updateBuildingRequirements(BuildingRequirements buildingRequirements, Long id)
    {

        BuildingRequirements buildingRequirements1 = buildingRequrirementsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak danych budynku o podanym numerze id: " + id));
        buildingRequirements1.update(buildingRequirements);

        buildingRequrirementsRepository.save(buildingRequirements1);
    }


    public void deleteBuildingRequirementsById(Long id) {
        buildingRequrirementsRepository.deleteById(id);
    }

}
