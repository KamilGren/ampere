package pl.gren.oze_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.HashSet;
import java.util.Set;

@Entity
public class COBufferTank extends Product {


    @NotBlank
    private String name; // Zmieniono nazwę nazwy na "name"

    @NotBlank
    private String material;

    @Positive
    private int capacity; // Zmieniono nazwę pojemności na "capacity"

    @Positive
    private int height; // Zmieniono nazwę wysokości na "height"

    @Positive
    private int diameter; // Zmieniono nazwę średnicy na "diameter"

    @NotBlank
    private String erpClass; // Zmieniono nazwę klasy ERP na "erpClass"

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getErpClass() {
        return erpClass;
    }

    public void setErpClass(String erpClass) {
        this.erpClass = erpClass;
    }

}

