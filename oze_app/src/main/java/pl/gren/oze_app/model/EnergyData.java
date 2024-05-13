package pl.gren.oze_app.model;

import pl.gren.oze_app.Reader.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnergyData {

    private BuildingRequirements buildingRequirements;
    private HeatPump heatPump1;
    private HeatPump heatPump2;
    private List<Integer> externalTemperature = new ArrayList<>();


    private String city;
    private int hours;
    private String pumpChart1Name;
    private double pumpChart1Value;
    private String pumpChart2Name;
    private double pumpChart2Value;
    private int heaterChart1Value;
    private int heaterChart2Value;
    private double sumPCValue;
    private double powerWithHeaterValue;
    private double cwuValue;
    private int heatingValue;
    private double energyDemandValue;
    private double pc1ConsumptionValue;
    private double pc2ConsumptionValue;
    private double totalPCConsumptionValue;
    CSVReader csvReader;



    public EnergyData(HeatPump heatPump1, HeatPump heatPump2, BuildingRequirements buildingRequirements) {
        this.heatPump1 = heatPump1;
        this.heatPump2 = heatPump2;
        this.buildingRequirements = buildingRequirements;
        this.externalTemperature = fillExternalTemperature();
        this.csvReader = new CSVReader();

    }

    public EnergyData(HeatPump heatPump, BuildingRequirements buildingRequirements) {
        this.heatPump1 = heatPump;
        this.buildingRequirements = buildingRequirements;
        this.externalTemperature = fillExternalTemperature();
        this.csvReader = new CSVReader();

    }

    public List<Integer> fillExternalTemperature() {
        List<Integer> externalTemperatureList = new ArrayList<>();
        for (int i = -20; i <= 30; i++) {
            externalTemperatureList.add(i);
        }
        return externalTemperatureList;
    }

    // lista wydajnosci grzewczej w porownaniu do konkretnych temperatur
    public List<Double> heatPumpChartValueList(HeatPump heatPump) {

        List<Double> heatPumpHeatingEfficiencyList = new ArrayList<>();
        List<Integer> temperatures = this.getExternalTemperature();

        for (int i = 0; i < this.getExternalTemperature().size(); i++) {
            heatPumpHeatingEfficiencyList.add(getHeatPumpChartValue(heatPump, temperatures.get(i)));
        }
        return heatPumpHeatingEfficiencyList;
    }


    public double getHeatPumpChartValue(HeatPump heatPump, int temperature) {
        switch (temperature) {
            case -20:
                return heatPump.getHeatingEfficiencyMinus20();
            case -15:
                return heatPump.getHeatingEfficiencyMinus15();
            case -7:
                return heatPump.getHeatingEfficiencyMinus7();
            case -2:
                return heatPump.getHeatingEfficiencyMinus2();
            case 2:
                return heatPump.getHeatingEfficiency2();
            case 7:
                return heatPump.getHeatingEfficiency7();
            case 12:
                return heatPump.getHeatingEfficiency12();
            case 15:
                return heatPump.getHeatingEfficiency15();
            case 20:
                return heatPump.getHeatingEfficiency20();
            default:
                // W przypadku pozostałych temperatur stosuj interpolację
                return getHeatPumpChartValueInterpolation(temperature, heatPump);
        }
    }

    public double getHeatPumpChartValueInterpolation(int temp, HeatPump heatPump) {
        double heatingEfficiencyInterpolation;

        if (temp > -20 && temp < -15) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiencyMinus15() - heatPump.getHeatingEfficiencyMinus20()) * (-20 - temp) / (-20 - (-15)) + heatPump.getHeatingEfficiencyMinus20();
        } else if (temp >= -15 && temp < -7) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiencyMinus7() - heatPump.getHeatingEfficiencyMinus15()) * (-15 - temp) / (-15 - (-7)) + heatPump.getHeatingEfficiencyMinus15();
        } else if (temp >= -7 && temp < -2) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiencyMinus2() - heatPump.getHeatingEfficiencyMinus7()) * (-7 - temp) / (-7 - (-2)) + heatPump.getHeatingEfficiencyMinus7();
        } else if (temp >= -2 && temp < 2) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiency2() - heatPump.getHeatingEfficiencyMinus2()) * (-2 - temp) / (-2 - 2) + heatPump.getHeatingEfficiencyMinus2();
        } else if (temp >= 2 && temp < 7) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiency7() - heatPump.getHeatingEfficiency2()) * (2 - temp) / (2 - 7) + heatPump.getHeatingEfficiency2();
        } else if (temp >= 7 && temp < 12) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiency12() - heatPump.getHeatingEfficiency7()) * (7 - temp) / (7 - 12) + heatPump.getHeatingEfficiency7();
        } else if (temp >= 12 && temp < 15) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiency15() - heatPump.getHeatingEfficiency12()) * (12 - temp) / (12 - 15) + heatPump.getHeatingEfficiency12();
        } else if (temp >= 15 && temp < 20) {
            heatingEfficiencyInterpolation = (heatPump.getHeatingEfficiency20() - heatPump.getHeatingEfficiency15()) * (15 - temp) / (15 - 20) + heatPump.getHeatingEfficiency15();
        } else if (temp >= 20) {
            heatingEfficiencyInterpolation = heatPump.getHeatingEfficiency20();
        } else {
            heatingEfficiencyInterpolation = 0.0;
        }
        // Dla innych wartości temperatury - zwróć dowolną wartość, tutaj możesz dostosować do własnych potrzeb


        return Math.round(heatingEfficiencyInterpolation * 100.0) / 100.0;
    }

    // dla dowolnej pompy ciepla,w wykresie sa dwie wiec bedziemy wywolywac dwa razy i dla roznych temperatur
    public double getHeatPumpWear(HeatPump heatPump, int temperature) {
        switch (temperature) {
            case -20:
                return heatPump.getEnergyConsumptionMinus20();
            case -15:
                return heatPump.getEnergyConsumptionMinus15();
            case -7:
                return heatPump.getEnergyConsumptionMinus7();
            case -2:
                return heatPump.getEnergyConsumptionMinus2();
            case 2:
                return heatPump.getEnergyConsumption2();
            case 7:
                return heatPump.getEnergyConsumption7();
            case 12:
                return heatPump.getEnergyConsumption12();
            case 15:
                return heatPump.getEnergyConsumption15();
            case 20:
                return heatPump.getEnergyConsumption20();
            default:
                // W przypadku pozostałych temperatur stosuj interpolację
                return getHeatPumpWearInterpolation(temperature, heatPump);
        }
    }

    public double getHeatPumpWearInterpolation(int temp, HeatPump heatPump) {
        double energyConsumptionInterpolation;

        if (temp > -20 && temp < -15) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumptionMinus15() - heatPump.getEnergyConsumptionMinus20()) * (-20 - temp) / (-20 - (-15)) + heatPump.getEnergyConsumptionMinus20();
        } else if (temp >= -15 && temp < -7) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumptionMinus7() - heatPump.getEnergyConsumptionMinus15()) * (-15 - temp) / (-15 - (-7)) + heatPump.getEnergyConsumptionMinus15();
        } else if (temp >= -7 && temp < -2) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumptionMinus2() - heatPump.getEnergyConsumptionMinus7()) * (-7 - temp) / (-7 - (-2)) + heatPump.getEnergyConsumptionMinus7();
        } else if (temp >= -2 && temp < 2) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumption2() - heatPump.getEnergyConsumptionMinus2()) * (-2 - temp) / (-2 - 2) + heatPump.getEnergyConsumptionMinus2();
        } else if (temp >= 2 && temp < 7) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumption7() - heatPump.getEnergyConsumption2()) * (2 - temp) / (2 - 7) + heatPump.getEnergyConsumption2();
        } else if (temp >= 7 && temp < 12) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumption12() - heatPump.getEnergyConsumption7()) * (7 - temp) / (7 - 12) + heatPump.getEnergyConsumption7();
        } else if (temp >= 12 && temp < 15) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumption15() - heatPump.getEnergyConsumption12()) * (12 - temp) / (12 - 15) + heatPump.getEnergyConsumption12();
        } else if (temp >= 15 && temp < 20) {
            energyConsumptionInterpolation = (heatPump.getEnergyConsumption20() - heatPump.getEnergyConsumption15()) * (15 - temp) / (15 - 20) + heatPump.getEnergyConsumption15();
        } else if (temp >= 20) {
            energyConsumptionInterpolation = heatPump.getEnergyConsumption20();
        } else {
            // Dla innych wartości temperatury - zwróć dowolną wartość, tutaj możesz dostosować do własnych potrzeb
            energyConsumptionInterpolation = 0.0;
        }

        return Math.round(energyConsumptionInterpolation * 100.0) / 100.0;
    }

    public BuildingRequirements getBuildingRequirements() {
        return buildingRequirements;
    }

    public void setBuildingRequirements(BuildingRequirements buildingRequirements) {
        this.buildingRequirements = buildingRequirements;
    }

    public HeatPump getHeatPump1() {
        return heatPump1;
    }

    public void setHeatPump1(HeatPump heatPump1) {
        this.heatPump1 = heatPump1;
    }

    public HeatPump getHeatPump2() {
        return heatPump2;
    }

    public void setHeatPump2(HeatPump heatPump2) {
        this.heatPump2 = heatPump2;
    }

    public List<Integer> getExternalTemperature() {

        return this.externalTemperature;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public int getHours(int temperature) throws IOException {
//
//        int sumHoursOfTemperature = 0;
//
//        CSVReader csvReader = new CSVReader();
//
//        try {
//            Map<String, Integer> temperatureOccurrences = csvReader.loadTemperatures(temperature); // Podaj interesującą temperaturę
//            for (Map.Entry<String, Integer> entry : temperatureOccurrences.entrySet()) {
//                System.out.println("Liczba godzin o temperaturze -1°C w " + entry.getKey() + ": " + entry.getValue());
//                sumHoursOfTemperature += entry.getValue();
//                System.out.println("Liczba godzin o temperaturze -1°C: " + entry.getValue());
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    return sumHoursOfTemperature;
//    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getPumpChart1Name() {
        return pumpChart1Name;
    }

    public void setPumpChart1Name(String pumpChart1Name) {
        this.pumpChart1Name = pumpChart1Name;
    }

    public void setPumpChart1Value(double pumpChart1Value) {
        this.pumpChart1Value = pumpChart1Value;
    }

    public String getPumpChart2Name() {
        return pumpChart2Name;
    }

    public void setPumpChart2Name(String pumpChart2Name) {
        this.pumpChart2Name = pumpChart2Name;
    }

    public void setPumpChart2Value(double pumpChart2Value) {
        this.pumpChart2Value = pumpChart2Value;
    }

    public int getHeaterChart1Value() {
        return heatPump1.getHeater();
    }

    public int getHeaterChart2Value() {
        return heatPump2.getHeater();
    }

    public double getSumPCValue() {
        return sumPCValue = getHeaterChart1Value() + getHeaterChart2Value();
    }

    public double getPowerWithHeaterValue() {
        return powerWithHeaterValue = getHeaterChart1Value() + getHeaterChart2Value() + getHeatPump1().getPower() + getHeatPump2().getPower();
    }


    public double getCwuValue() {
        return buildingRequirements.getCWUValue();
    }


    public double getHeatingValue(double temperature) {

        double heatPumpOnTemperature = 24;
        double heatingCO = buildingRequirements.getCOValue();
        double heatingValue = 0;

        double heatingValueForMinus20 = heatingCO;


        if (temperature <= -17) {
            return heatingValueForMinus20;
        } else if (heatPumpOnTemperature >= temperature) {
            heatingValue = heatingValueForMinus20 - (heatingValueForMinus20 / (17 + heatPumpOnTemperature + 1) * (17 - (temperature * -1)));


            return heatingValue;


        } else
            return 0;
    }


    public double getEnergyDemandValue(Integer temperature) {

        if (buildingRequirements.getHeatingPumpSetTemperature() >= temperature) {
            return energyDemandValue = this.getHeatingValue(temperature) + this.getCwuValue();
        } else
            return 0;
    }

    public double getTotalPCConsumptionValue(int temperature) {
        return totalPCConsumptionValue = getHeatPumpWear(heatPump1, temperature) + getHeatPumpWear(heatPump2, temperature);
    }

    public void setExternalTemperature(List<Integer> externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public void setHeaterChart1Value(int heaterChart1Value) {
        this.heaterChart1Value = heaterChart1Value;
    }

    public void setHeaterChart2Value(int heaterChart2Value) {
        this.heaterChart2Value = heaterChart2Value;
    }

    public void setSumPCValue(double sumPCValue) {
        this.sumPCValue = sumPCValue;
    }

    public void setPowerWithHeaterValue(double powerWithHeaterValue) {
        this.powerWithHeaterValue = powerWithHeaterValue;
    }

    public void setCwuValue(double cwuValue) {
        this.cwuValue = cwuValue;
    }

    public void setHeatingValue(int heatingValue) {
        this.heatingValue = heatingValue;
    }

    public void setEnergyDemandValue(double energyDemandValue) {
        this.energyDemandValue = energyDemandValue;
    }

    public void setPc1ConsumptionValue(double pc1ConsumptionValue) {
        this.pc1ConsumptionValue = pc1ConsumptionValue;
    }

    public void setPc2ConsumptionValue(double pc2ConsumptionValue) {
        this.pc2ConsumptionValue = pc2ConsumptionValue;
    }

    public void setTotalPCConsumptionValue(double totalPCConsumptionValue) {
        this.totalPCConsumptionValue = totalPCConsumptionValue;
    }

    // oblicza CO dla danego miesiaca, sumujac CO dla kazdej temperatury
    public double getMonthCO(String month) throws IOException {

        double COforTemp = 0;
        double monthCO = 0;
        CSVReader csvReader = new CSVReader();


        for (int i = -20; i < 30; i++) {
            COforTemp = getHeatPumpWear(getHeatPump1(), i) / getHeatPumpChartValue(getHeatPump1(), i) * getHeatingValue(i) * csvReader.countHoursWithTemperature(month, i);
            monthCO += COforTemp;


// Wyświetlenie wartości każdej używanej zmiennej
//            System.out.println("Heat pump wear: " + getHeatPumpWear(getHeatPump1(), i));
//            System.out.println("Wartość PumpChart1Value dla stopni:  " + i + " " + getHeatPumpChartValue(getHeatPump1(), i));
//            System.out.println("Wartość HeatingValue(" + i + "): " + getHeatingValue(i));
//            System.out.println("Liczba godzin dla temperatury " + i + " stopni w miesiącu " + month + ": " + csvReader.countHoursWithTemperature(month, i));
//            System.out.println("COforTemp: " + COforTemp);
        }

        System.out.println("Month CO: " + monthCO);
        return Math.round(monthCO * 100.0) / 100.0;
    }

    public double getYearCO() throws IOException {

        double yearCO = 0;

        yearCO += getMonthCO("styczeń");
        yearCO += getMonthCO("luty");
        yearCO += getMonthCO("marzec");
        yearCO += getMonthCO("kwiecień");
        yearCO += getMonthCO("maj");
        yearCO += getMonthCO("czerwiec");
        yearCO += getMonthCO("lipiec");
        yearCO += getMonthCO("sierpień");
        yearCO += getMonthCO("wrzesień");
        yearCO += getMonthCO("październik");
        yearCO += getMonthCO("listopad");
        yearCO += getMonthCO("grudzień");

        System.out.println("Year CO to: " + yearCO);

        return yearCO;
    }

    public double getMonthCWU(String month) throws IOException {

        double CWUforTemp = 0;
        double monthCWU = 0;

        for (int i = -20; i < 30; i++) {
            CWUforTemp = getHeatPumpWear(getHeatPump1(), i) / getHeatPumpChartValue(getHeatPump1(), i) * getCwuValue() * csvReader.countHoursWithTemperature(month, i) / 2.8;
            monthCWU += CWUforTemp;
        }
        System.out.println("Month CWU dla " + month + "to : " + monthCWU);
        return Math.round(monthCWU * 100.0) / 100.0;
    }

    public double getYearCWU() throws IOException {

        double yearCWU = 0;

        yearCWU += getMonthCWU("styczeń");
        yearCWU += getMonthCWU("luty");
        yearCWU += getMonthCWU("marzec");
        yearCWU += getMonthCWU("kwiecień");
        yearCWU += getMonthCWU("maj");
        yearCWU += getMonthCWU("czerwiec");
        yearCWU += getMonthCWU("lipiec");
        yearCWU += getMonthCWU("sierpień");
        yearCWU += getMonthCWU("wrzesień");
        yearCWU += getMonthCWU("październik");
        yearCWU += getMonthCWU("listopad");
        yearCWU += getMonthCWU("grudzień");

        System.out.println("Year CWU to: " + yearCWU);

        return yearCWU;
    }

    public double getYearCostOfExploitation() throws IOException {
        return this.getYearCWU() + this.getYearCO();
    }


    public double getYearEnergyProduced() throws IOException {

        double yearEnergyProduced = 0;

        for(int i = -20; i < 30; i++) {
            yearEnergyProduced += getHeatingValue(i) * csvReader.getCountOfYearTempHours((double) i);

            System.out.println("Year energy produced: " + yearEnergyProduced);
        }

        return yearEnergyProduced;
    }

    public double getSCOP() throws IOException {

        System.out.println("SCOP:" + getYearEnergyProduced() / getYearCO() );

        return getYearEnergyProduced() / getYearCO();
    }




}
