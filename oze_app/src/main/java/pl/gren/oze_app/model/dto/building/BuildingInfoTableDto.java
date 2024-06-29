package pl.gren.oze_app.model.dto.building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingInfoTableDto {
    private String location;
    private Double locationTemperature;
    private Integer buildingType;
    private Double x;
    private Double y;
    private Integer heatedArea;
    private Double ceilingHeight;
    private Double heatingTemperature;
    private Double coolingTemperature;
    private Integer hasBasement;
    private Integer wallType;
    private Double wallThickness;
    private Integer wallInsulationType;
    private Double wallInsulationThickness;
    private Integer roofInsulationType;
    private Double roofInsulationThickness;
    private Integer floorInsulationType;
    private Double floorInsulationThickness;
    private Integer windowGlazingType;
    private Integer windowCount;
    private Integer doorCount;
    private Integer ventType;
    private Integer fuelType;
    private Double fuelAmount;
    private Integer peopleCount;
    private Integer waterUsageType;
}
