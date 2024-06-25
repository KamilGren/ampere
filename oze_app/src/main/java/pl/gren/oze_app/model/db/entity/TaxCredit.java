package pl.gren.oze_app.model.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tax_credit")
public class TaxCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentage")
    private BigDecimal percentage;

    @Override
    public String toString() {
        return "TaxCredit{" +
                "id=" + id +
                ", percentage=" + percentage +
                '}';
    }

    @Override
    public int hashCode() {
        return 16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxCredit taxCredit = (TaxCredit) o;
        return Objects.equals(getId(), taxCredit.getId()) && Objects.equals(getPercentage(), taxCredit.getPercentage());
    }
}
