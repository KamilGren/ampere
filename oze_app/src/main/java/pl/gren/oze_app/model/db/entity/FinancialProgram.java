package pl.gren.oze_app.model.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "financial_program")
public class FinancialProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private BigDecimal percentage;

    @Column(name = "limit")
    private BigDecimal limit;

    private static AtomicInteger RANDOM_HASH_CODE = new AtomicInteger(1);
    private final int HASH_CODE = RANDOM_HASH_CODE.getAndIncrement();

    @Override
    public int hashCode() {
        return HASH_CODE;
    }

    @Override
    public String toString() {
        return "FinancialProgram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", limit=" + limit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialProgram that = (FinancialProgram) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPercentage(), that.getPercentage()) && Objects.equals(getLimit(), that.getLimit());
    }
}
