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

    @Column(name = "condition")
    private String condition;

    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public String toString() {
        return "FinancialProgram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", limit=" + limit +
                ", condition='" + condition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialProgram that = (FinancialProgram) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPercentage(), that.getPercentage()) && Objects.equals(getLimit(), that.getLimit()) && Objects.equals(getCondition(), that.getCondition());
    }
}
