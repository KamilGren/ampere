package pl.gren.oze_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CWUBufforTank extends Product {

    @NotBlank(message = "Please provide name")
    private String name;

    @NotBlank(message = "Please provide material")
    private String material;

    @NotNull(message = "Please provide capacity")
    @Min(value = 0, message = "Capacity must be non-negative")
    private Integer capacity;

    private String coil;

    private Integer height;

    @Min(value = 0, message = "Diameter must be non-negative")
    private Integer diameter;

    @NotBlank(message = "Please provide ERP class")
    private String erpClass;

    @NotBlank(message = "Please provide heater information")
    private String heater;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="cwuBufforTank")
    private Set<ClientProducts> cwuBufforTankClientList = new HashSet<>();

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(Double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCoil() {
        return coil;
    }

    public void setCoil(String coil) {
        this.coil = coil;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public String getErpClass() {
        return erpClass;
    }

    public void setErpClass(String erpClass) {
        this.erpClass = erpClass;
    }

    public String getHeater() {
        return heater;
    }

    public void setHeater(String heater) {
        this.heater = heater;
    }

    public Set<ClientProducts> getCwuBufforTankClientList() {
        return cwuBufforTankClientList;
    }

    public void setCwuBufforTankClientList(Set<ClientProducts> cwuBufforTankClientList) {
        this.cwuBufforTankClientList = cwuBufforTankClientList;
    }
}
