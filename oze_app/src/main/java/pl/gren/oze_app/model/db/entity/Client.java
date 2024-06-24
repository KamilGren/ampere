package pl.gren.oze_app.model.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<BuildingInfo> buildings = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

    public List<BuildingInfo> getBuildings() {
        List<BuildingInfo> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.getBuildingInfo() != null) {
                result.add(o.getBuildingInfo());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client that = (Client) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getClass());
    }
}
