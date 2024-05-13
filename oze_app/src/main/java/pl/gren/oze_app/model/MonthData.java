package pl.gren.oze_app.model;

public class MonthData {

    private double CO;
    private double COCost;
    private double CWU;
    private double CWUCost;
    private double kwhCost;


    public MonthData(double co, double cwu, double kwhCost) {
        this.CO = co;
        this.CWU = cwu;
        this.kwhCost = kwhCost;
        this.COCost = Math.round(co * kwhCost * 100.0) / 100.0;
        this.CWUCost = Math.round(cwu * kwhCost * 100.0) / 100.0;
    }

    public double getCOCost() {
        return COCost;
    }

    public void setCOCost(double COCost) {
        this.COCost = COCost;
    }

    public double getCWUCost() {
        return CWUCost;
    }

    public void setCWUCost(double CWUCost) {
        this.CWUCost = CWUCost;
    }

    public double getCO() {
        return CO;
    }

    public void setCO(double CO) {
        this.CO = CO;
    }

    public double getCWU() {
        return CWU;
    }

    public void setCWU(double CWU) {
        this.CWU = CWU;
    }
}
