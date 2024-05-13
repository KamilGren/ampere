package pl.gren.oze_app.model.offer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String bank;
    Double creditAmount;
    Double RRSO;
    // 12/24/36...
    Double numberOfLoanInstallmentsInYear;
    // all installments amount
    Double numberOfLoanInstallmentsAll;
    Double loanInstallmentAmount;
    Double creditCost;
    boolean afterCoFinancing;

    public Credit() {
    }

    public Credit(Long id, String bank, Double creditAmount, Double RRSO, Double numberOfLoanInstallmentsInYear, Double numberOfLoanInstallmentsAll, Double loanInstallmentAmount, Double creditCost, boolean afterCoFinancing) {
        this.id = id;
        this.bank = bank;
        this.creditAmount = creditAmount;
        this.RRSO = RRSO;
        this.numberOfLoanInstallmentsInYear = numberOfLoanInstallmentsInYear;
        this.numberOfLoanInstallmentsAll = numberOfLoanInstallmentsAll;
        this.loanInstallmentAmount = loanInstallmentAmount;
        this.creditCost = creditCost;
        this.afterCoFinancing = afterCoFinancing;
    }

    public Credit(Credit credit) {
    }

    //    public Map<Integer, Integer> createData() {
//
//        Map<Integer, Integer> numberOfLoanInstallmentsInYear = new HashMap<>();
//
//        for (int i = 1; i < 12; i++) {
//            numberOfLoanInstallmentsInYear.put(i, i*12);
//        }
//        return numberOfLoanInstallmentsInYear;
//    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getRRSO() {
        return RRSO;
    }

    public void setRRSO(Double RRSO) {
        this.RRSO = RRSO;
    }

    public Double getNumberOfLoanInstallmentsInYear() {
        return numberOfLoanInstallmentsInYear;
    }

    public void setNumberOfLoanInstallmentsInYear(Double numberOfLoanInstallmentsInYear) {
        this.numberOfLoanInstallmentsInYear = numberOfLoanInstallmentsInYear;
    }

    public Double getNumberOfLoanInstallmentsAll() {
        return numberOfLoanInstallmentsAll;
    }

    public void setNumberOfLoanInstallmentsAll(Double numberOfLoanInstallmentsAll) {
        this.numberOfLoanInstallmentsAll = numberOfLoanInstallmentsAll;
    }

    // wyliczanie wysokosci raty;
    public Double getLoanInstallmentAmount() {

        return loanInstallmentAmount;
        //return (this.creditAmount * this.RRSO) / (numberOfLoanInstallmentsInYear * (1 - (Math.pow(numberOfLoanInstallmentsInYear, numberOfLoanInstallmentsAll) / (Math.pow((numberOfLoanInstallmentsInYear + numberOfLoanInstallmentsAll), numberOfLoanInstallmentsInYear)))));
    }

    public void setLoanInstallmentAmount(Double loanInstallmentAmount) {
        this.loanInstallmentAmount = loanInstallmentAmount;
    }

    // koszty kredytu
    public Double getCreditCost() {
        return creditCost;
        // return (getLoanInstallmentAmount() * getNumberOfLoanInstallmentsAll()) - getCreditAmount();
    }

    public void setCreditCost(Double creditCost) {
        this.creditCost = creditCost;
    }

    public boolean isAfterCoFinancing() {
        return afterCoFinancing;
    }

    public void setAfterCoFinancing(boolean afterCoFinancing) {
        this.afterCoFinancing = afterCoFinancing;
    }





}
