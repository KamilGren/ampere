package pl.gren.oze_app.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String role;
    public String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="salesman")
    public Set<Client> clientList = new HashSet<>();

    public Salesman(Long id, String name, String surname, String password, String role, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public Salesman() {

    }

    public void updateSalesman(Salesman updatedSalesman) {
        this.name = updatedSalesman.getName();
        this.surname = updatedSalesman.getSurname();
        this.password =  updatedSalesman.getPassword();
        this.email = updatedSalesman.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addClient(Client client) {
        this.clientList.add(client);
    }


    public Set<Client> getClientList() {
        return clientList;
    }

    public void setClientList(Set<Client> clientList) {
        this.clientList = clientList;
    }
}