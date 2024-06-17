package pl.gren.oze_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class HeatPump extends Product {

    @NotBlank
    @Size(min = 1, max = 255)
    private String type;

    @NotBlank
    @Size(min = 1, max = 255)
    private String externalUnit;

    @NotBlank
    @Size(min = 1, max = 255)
    private String internalUnit;

    @Positive(message = "Power must be a positive number")
    private int power;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer = 2, fraction = 2)
    private int heater;

    @DecimalMin(value = "0.0", message = "Value must be greater than 0.0")
    private double scop;

    @Positive(message = "Power supply must be a positive number")
    private int powerSupply;

    @PositiveOrZero(message = "Warranty must be a non-negative number")
    private int warranty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="heatPump")
    private Set<ClientProducts> heatpumpClientList = new HashSet<>();


    @Min(0)
    private double heatingEfficiencyMinus20;

    @Min(0)
    private double heatingEfficiencyMinus15;

    @Min(0)
    private double heatingEfficiencyMinus7;

    @Min(0)
    private double heatingEfficiencyMinus2;

    @Min(0)
    private double heatingEfficiency2;

    @Min(0)
    private double heatingEfficiency7;

    @Min(0)
    private double heatingEfficiency12;

    @Min(0)
    private double heatingEfficiency15;

    @Min(0)
    private double heatingEfficiency20;

    @Min(0)
    private double energyConsumptionMinus20;

    @Min(0)
    private double energyConsumptionMinus15;

    @Min(0)
    private double energyConsumptionMinus7;

    @Min(0)
    private double energyConsumptionMinus2;

    @Min(0)
    private double energyConsumption2;

    @Min(0)
    private double energyConsumption7;

    @Min(0)
    private double energyConsumption12;

    @Min(0)
    private double energyConsumption15;

    @Min(0)
    private double energyConsumption20;

    public Set<ClientProducts> getHeatpumpClientList() {
        return heatpumpClientList;
    }

    public void setHeatpumpClientList(Set<ClientProducts> heatpumpClientList) {
        this.heatpumpClientList = heatpumpClientList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getExternalUnit() {
        return externalUnit;
    }

    public void setExternalUnit(String externalUnit) {
        this.externalUnit = externalUnit;
    }

    public String getInternalUnit() {
        return internalUnit;
    }

    public void setInternalUnit(String internalUnit) {
        this.internalUnit = internalUnit;
    }

    public void setCatalogPrice(double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHeater() {
        return heater;
    }

    public void setHeater(int heater) {
        this.heater = heater;
    }

    public double getScop() {
        return scop;
    }

    public void setScop(double scop) {
        this.scop = scop;
    }

    public int getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(int powerSupply) {
        this.powerSupply = powerSupply;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public double getHeatingEfficiencyMinus20() {
        return heatingEfficiencyMinus20;
    }

    public void setHeatingEfficiencyMinus20(double heatingEfficiencyMinus20) {
        this.heatingEfficiencyMinus20 = heatingEfficiencyMinus20;
    }

    public double getHeatingEfficiencyMinus15() {
        return heatingEfficiencyMinus15;
    }

    public void setHeatingEfficiencyMinus15(double heatingEfficiencyMinus15) {
        this.heatingEfficiencyMinus15 = heatingEfficiencyMinus15;
    }

    public double getHeatingEfficiencyMinus7() {
        return heatingEfficiencyMinus7;
    }

    public void setHeatingEfficiencyMinus7(double heatingEfficiencyMinus7) {
        this.heatingEfficiencyMinus7 = heatingEfficiencyMinus7;
    }

    public double getHeatingEfficiencyMinus2() {
        return heatingEfficiencyMinus2;
    }

    public void setHeatingEfficiencyMinus2(double heatingEfficiencyMinus2) {
        this.heatingEfficiencyMinus2 = heatingEfficiencyMinus2;
    }

    public double getHeatingEfficiency2() {
        return heatingEfficiency2;
    }

    public void setHeatingEfficiency2(double heatingEfficiency2) {
        this.heatingEfficiency2 = heatingEfficiency2;
    }

    public double getHeatingEfficiency7() {
        return heatingEfficiency7;
    }

    public void setHeatingEfficiency7(double heatingEfficiency7) {
        this.heatingEfficiency7 = heatingEfficiency7;
    }

    public double getHeatingEfficiency12() {
        return heatingEfficiency12;
    }

    public void setHeatingEfficiency12(double heatingEfficiency12) {
        this.heatingEfficiency12 = heatingEfficiency12;
    }

    public double getHeatingEfficiency15() {
        return heatingEfficiency15;
    }

    public void setHeatingEfficiency15(double heatingEfficiency15) {
        this.heatingEfficiency15 = heatingEfficiency15;
    }

    public double getHeatingEfficiency20() {
        return heatingEfficiency20;
    }

    public void setHeatingEfficiency20(double heatingEfficiency20) {
        this.heatingEfficiency20 = heatingEfficiency20;
    }

    public double getEnergyConsumptionMinus20() {
        return energyConsumptionMinus20;
    }

    public void setEnergyConsumptionMinus20(double energyConsumptionMinus20) {
        this.energyConsumptionMinus20 = energyConsumptionMinus20;
    }

    public double getEnergyConsumptionMinus15() {
        return energyConsumptionMinus15;
    }

    public void setEnergyConsumptionMinus15(double energyConsumptionMinus15) {
        this.energyConsumptionMinus15 = energyConsumptionMinus15;
    }

    public double getEnergyConsumptionMinus7() {
        return energyConsumptionMinus7;
    }

    public void setEnergyConsumptionMinus7(double energyConsumptionMinus7) {
        this.energyConsumptionMinus7 = energyConsumptionMinus7;
    }

    public double getEnergyConsumptionMinus2() {
        return energyConsumptionMinus2;
    }

    public void setEnergyConsumptionMinus2(double energyConsumptionMinus2) {
        this.energyConsumptionMinus2 = energyConsumptionMinus2;
    }

    public double getEnergyConsumption2() {
        return energyConsumption2;
    }

    public void setEnergyConsumption2(double energyConsumption2) {
        this.energyConsumption2 = energyConsumption2;
    }

    public double getEnergyConsumption7() {
        return energyConsumption7;
    }

    public void setEnergyConsumption7(double energyConsumption7) {
        this.energyConsumption7 = energyConsumption7;
    }

    public double getEnergyConsumption12() {
        return energyConsumption12;
    }

    public void setEnergyConsumption12(double energyConsumption12) {
        this.energyConsumption12 = energyConsumption12;
    }

    public double getEnergyConsumption15() {
        return energyConsumption15;
    }

    public void setEnergyConsumption15(double energyConsumption15) {
        this.energyConsumption15 = energyConsumption15;
    }

    public double getEnergyConsumption20() {
        return energyConsumption20;
    }

    public void setEnergyConsumption20(double energyConsumption20) {
        this.energyConsumption20 = energyConsumption20;
    }

    public void addToHeatPumpClientList(ClientProducts clientProducts) {
        this.heatpumpClientList.add(clientProducts);
    }


}
