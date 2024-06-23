package pl.gren.oze_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class _Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String note;

    private String telephone;

    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "salesman_id")
    private _Salesman salesman;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_products_id")
    private ClientProducts clientProducts;

    @OneToOne
    @JoinColumn(name = "building_requirements_id")
    private BuildingRequirements buildingRequirements;

    public BuildingRequirements getBuildingRequirements() {
        return buildingRequirements;
    }

    public void setBuildingRequirements(BuildingRequirements buildingRequirements) {
        this.buildingRequirements = buildingRequirements;
    }

    public _Client() {
    }

    public _Client(Long id, _Salesman salesman) {
        this.id = id;
        this.salesman = salesman;
    }

    public _Client(Long id, _Salesman salesman, BuildingRequirements buildingRequirements) {
        this.id = id;
        this.salesman = salesman;
        this.buildingRequirements = buildingRequirements;
    }

    public _Client(Long id, String name, String address, _Salesman salesman, String note, String telephone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salesman = salesman;
        this.note = note;
        this.telephone = telephone;
        this.createTime = LocalDateTime.now();
    }

    public void updateClient(_Client updatedClient) {
        this.name = updatedClient.getName();
        this.address = updatedClient.getAddress();
        this.note = updatedClient.getNote();
        this.salesman = updatedClient.getSalesman();
        this.telephone = updatedClient.getTelephone();
    }


    public ClientProducts getClientProducts() {
        return clientProducts;
    }

    public void setClientProducts(ClientProducts clientProducts) {
        this.clientProducts = clientProducts;
    }

    public void updateClientProducts(ClientProducts clientProducts) {
        this.getClientProducts().updateClientProducts(clientProducts);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public _Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(_Salesman salesman) {
        this.salesman = salesman;
    }
}