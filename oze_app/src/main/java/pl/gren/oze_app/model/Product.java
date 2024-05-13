package pl.gren.oze_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    String model;

    @NotBlank
    @Size(min = 1, max = 255)
    String producent;

    @PositiveOrZero(message = "Catalog price must be a non-negative number")
    Double catalogPrice;

    @PositiveOrZero(message = "Catalog price must be a non-negative number")
    double discount;

    @ManyToMany(mappedBy = "products")
    Set<ClientProducts> productClientList = new HashSet<>();

    @PositiveOrZero(message = "Catalog price must be a non-negative number")
    Double purchasePrice;

    double priceWithOverhead;

    double priceWithMargin;

    double priceWithVat;

    public Product() {
    }

    public Product(Long id, String model, String producent, double catalogPrice, double purchasePrice) {
        this.id = id;
        this.model = model;
        this.producent = producent;
        this.catalogPrice = catalogPrice;
        this.purchasePrice = purchasePrice;
    }

    public Product(Long id, String model, String producent, double purchasePrice) {
        this.id = id;
        this.model = model;
        this.producent = producent;
        this.purchasePrice = purchasePrice;
    }

    public Set<ClientProducts> getProductClientList() {
        return productClientList;
    }

    public void setProductClientList(Set<ClientProducts> productClientList) {
        this.productClientList = productClientList;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void priceWithOverhead(double overhead) {
        this.priceWithOverhead = getPurchasePrice() + overhead;
    }

    public void priceWithMargin(double margin) {
        this.priceWithMargin = getPriceWithOverhead() +  (getPriceWithOverhead() * (margin / 100));
    }

    public void priceWithVat(double vat) {
        this.priceWithVat = getPriceWithMargin() + (getPriceWithOverhead() * (vat / 100));
    }

    public double getPriceWithVat() {
        return priceWithVat;
    }

    public void setPriceWithVat(double priceWithVat) {
        this.priceWithVat = priceWithVat;
    }

    public double getPriceWithOverhead() {
        return priceWithOverhead;
    }

    public void setPriceWithOverhead(double priceWithOverhead) {
        this.priceWithOverhead = priceWithOverhead;
    }

    public double getPriceWithMargin() {
        return priceWithMargin;
    }

    public void setPriceWithMargin(double priceWithMargin) {
        this.priceWithMargin = priceWithMargin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
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
}



