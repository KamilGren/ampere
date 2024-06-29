package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import pl.gren.oze_app.model.db.enums.*;
import pl.gren.oze_app.model.db.repository.BuildingInfoRepository;
import pl.gren.oze_app.model.dto.building.BuildingInfoTableDto;

import java.math.BigDecimal;

@Service
public class BuildingService {

    @Autowired private final BuildingInfoRepository buildingInfoRepository;

    public BuildingService(BuildingInfoRepository buildingInfoRepository) {
        this.buildingInfoRepository = buildingInfoRepository;
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
}
