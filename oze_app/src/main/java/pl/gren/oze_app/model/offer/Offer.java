package pl.gren.oze_app.model.offer;

import jakarta.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private boolean creditYes;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @OneToOne
    @JoinColumn(name = "financial_program_id")
    private FinancialProgram financialProgram;


    public Offer() {
    }

    public Offer(boolean creditYes, Credit credit, FinancialProgram financialProgram) {
        this.creditYes = creditYes;
        this.credit = credit;
        this.financialProgram = financialProgram;
    }

    public boolean isCreditYes() {
        return creditYes;
    }

    public void setCreditYes(boolean creditYes) {
        this.creditYes = creditYes;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public FinancialProgram getFinancialProgram() {
        return financialProgram;
    }

    public void setFinancialProgram(FinancialProgram financialProgram) {
        this.financialProgram = financialProgram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
