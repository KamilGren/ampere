package pl.gren.oze_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClientDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String note;

    private String telephone;

    private LocalDateTime createTime;


    public ClientDTO() {
    }


    public void updateClient(ClientDTO updatedClient) {
        this.name = updatedClient.getName();
        this.address = updatedClient.getAddress();
        this.note = updatedClient.getNote();
        this.telephone = updatedClient.getTelephone();
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


}