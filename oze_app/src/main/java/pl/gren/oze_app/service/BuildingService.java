package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.enums.*;
import pl.gren.oze_app.model.db.repository.BuildingInfoRepository;
import pl.gren.oze_app.model.dto.building.BuildingInfoCreateDto;
import pl.gren.oze_app.model.dto.building.BuildingInfoTableDto;

import java.math.BigDecimal;

@Service
public class BuildingService {

    @Autowired private final BuildingInfoRepository buildingInfoRepository;
    @Autowired private final ClientService clientService;

    public BuildingService(BuildingInfoRepository buildingInfoRepository, ClientService clientService) {
        this.buildingInfoRepository = buildingInfoRepository;
        this.clientService = clientService;
    }

    public void saveChanges(BuildingInfo building, BuildingInfoTableDto dto) {
        building.setLocation(dto.getLocation());
        building.setLocationTemperatureCelcius(BigDecimal.valueOf(dto.getLocationTemperature()));
        building.setBuildingType(BuildingType.findById(dto.getBuildingType()).orElseThrow());
        building.setLengthMeters(BigDecimal.valueOf(dto.getX()));
        building.setWidthMeters(BigDecimal.valueOf(dto.getY()));
        building.setHeatedAreaSquareMeters(BigDecimal.valueOf(dto.getHeatedArea()));
        building.setCeilingHeightMeters(BigDecimal.valueOf(dto.getCeilingHeight()));
        building.setHeatingTemperatureCelcius(BigDecimal.valueOf(dto.getHeatingTemperature()));
        building.setCoolingTemperatureCelcius(BigDecimal.valueOf(dto.getCoolingTemperature()));
        building.setHasBasement(dto.getHasBasement() == 1);
        building.setWallType(WallType.findById(dto.getWallType()).orElseThrow());

        building.setWallThicknessCm(BigDecimal.valueOf(dto.getWallThickness()));
        building.setWallInsulationThicknessCm(BigDecimal.valueOf(dto.getWallInsulationThickness()));
        building.setRoofInsulationThicknessCm(BigDecimal.valueOf(dto.getRoofInsulationThickness()));
        building.setFloorInsulationThicknessCm(BigDecimal.valueOf(dto.getFloorInsulationThickness()));

        building.setWallInsulationType(InsulationType.findById(dto.getWallInsulationType()).orElseThrow());
        building.setRoofInsulationType(InsulationType.findById(dto.getRoofInsulationType()).orElseThrow());
        building.setFloorInsulationType(InsulationType.findById(dto.getFloorInsulationType()).orElseThrow());

        building.setWindowGlazingType(WindowGlazingType.findById(dto.getWindowGlazingType()).orElseThrow());
        building.setWindowCount(dto.getWindowCount());
        building.setExteriorDoorCount(dto.getDoorCount());
        building.setVentilationType(VentilationType.findById(dto.getVentType()).orElseThrow());
        building.setFuelType(FuelType.findById(dto.getFuelType()).orElseThrow());
        building.setFuelUsageAmount(BigDecimal.valueOf(dto.getFuelAmount()));
        building.setPeopleCount(dto.getPeopleCount());
        building.setWaterUsageType(WaterUsageType.findById(dto.getWaterUsageType()).orElseThrow());

        this.buildingInfoRepository.save(building);
    }

    public BuildingInfo insert(BuildingInfoCreateDto dto) {
        BuildingInfo building = new BuildingInfo();
        // TODO add name to building
        Client client = this.clientService.findById(dto.getClientId()).orElseThrow();
        building.setClient(client);
        building.setLocation("");
        building.setLocationTemperatureCelcius(BigDecimal.ZERO);
        building.setBuildingType(BuildingType.SINGLE_STORY);
        building.setLengthMeters(BigDecimal.ZERO);
        building.setWidthMeters(BigDecimal.ZERO);
        building.setHeatedAreaSquareMeters(BigDecimal.ZERO);
        building.setCeilingHeightMeters(BigDecimal.ZERO);
        building.setHeatingTemperatureCelcius(BigDecimal.ZERO);
        building.setCoolingTemperatureCelcius(BigDecimal.ZERO);
        building.setHasBasement(false);
        building.setWallType(WallType.CERAMIC_BRICK);
        building.setWallThicknessCm(BigDecimal.ZERO);
        building.setWallInsulationType(InsulationType.STYROFOAM);
        building.setRoofInsulationType(InsulationType.STYROFOAM);
        building.setFloorInsulationType(InsulationType.STYROFOAM);
        building.setWallInsulationThicknessCm(BigDecimal.ZERO);
        building.setRoofInsulationThicknessCm(BigDecimal.ZERO);
        building.setFloorInsulationThicknessCm(BigDecimal.ZERO);
        building.setWindowGlazingType(WindowGlazingType.DOUBLE);
        building.setWindowCount(0);
        building.setExteriorDoorCount(1);
        building.setVentilationType(VentilationType.GRAVITY);
        building.setFuelType(FuelType.NATURAL_GAS);
        building.setFuelUsageAmount(BigDecimal.ZERO);
        building.setPeopleCount(1);
        building.setWaterUsageType(WaterUsageType.SHOWER);
        return this.buildingInfoRepository.save(building);
    }
}
