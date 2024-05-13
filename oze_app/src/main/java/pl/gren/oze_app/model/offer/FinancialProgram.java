package pl.gren.oze_app.model.offer;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FinancialProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String value;
    String procentAmount;
    String conditions;

    public FinancialProgram() {
    }

    public FinancialProgram(Long id, String name, String value, String procentAmount, String conditions) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.procentAmount = procentAmount;
        this.conditions = conditions;
    }

    public FinancialProgram(FinancialProgram financialProgram) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getProcentAmount() {
        return procentAmount;
    }

    public void setProcentAmount(String procentAmount) {
        this.procentAmount = procentAmount;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }


}
