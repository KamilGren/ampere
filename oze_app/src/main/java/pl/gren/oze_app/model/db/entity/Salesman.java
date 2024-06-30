package pl.gren.oze_app.model.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salesman")
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "salesman", fetch = FetchType.LAZY)
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "salesman", fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>();

    public void addContract(Contract contract) {
        contract.setSalesman(this);
        this.contracts.add(contract);
    }

    public void addClient(Client client) {
        client.setSalesman(this);
        this.clients.add(client);
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 15;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(getId(), salesman.getId()) && Objects.equals(getFirstName(), salesman.getFirstName()) && Objects.equals(getLastName(), salesman.getLastName()) && Objects.equals(getUsername(), salesman.getUsername()) && Objects.equals(getEmail(), salesman.getEmail()) && Objects.equals(getPasswordHash(), salesman.getPasswordHash()) && Objects.equals(getRole(), salesman.getRole()) && Objects.equals(getContracts(), salesman.getContracts()) && Objects.equals(getClients(), salesman.getClients());
    }
}
