package pl.gren.oze_app.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CSVReader {

    private final String filePath = "Dane_meteo.csv";


    public static ArrayList<String> getMonthsList() {
        // Creating an ArrayList of months
        ArrayList<String> months = new ArrayList<>();

        // Adding months to the ArrayList
        months.add("styczeń");
        months.add("luty");
        months.add("marzec");
        months.add("kwiecień");
        months.add("maj");
        months.add("czerwiec");
        months.add("lipiec");
        months.add("sierpień");
        months.add("wrzesień");
        months.add("październik");
        months.add("listopad");
        months.add("grudzień");

        // Returning the ArrayList of months
        return months;
    }
    public double countHoursWithTemperature(String month, double targetTemperature) throws IOException {
        double temperature = 0;


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CSVReader.class.getClassLoader().getResourceAsStream(filePath)))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String currentMonth = parts[0];

                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line (header)
                }

                if (month == null || month.equalsIgnoreCase(currentMonth)) {
                    int column = (int) targetTemperature + 19; // Calculate the index of the column for the target temperature
                    if (column >= 1 && column < parts.length) {
                        temperature = Double.parseDouble(parts[column]);
                        System.out.println("Ilosc H temp dla miesiaca: " + month + "dla wybranej temperatury: " + targetTemperature + "to: " + temperature);

                    }
                }
            }
        }
        return temperature;
    }

    public double getCountOfYearTempHours(Double temp) throws IOException {

        double temperatureSum = 0;

        for (int i = 0; i < 12; i++) {
            temperatureSum += countHoursWithTemperature(getMonthsList().get(i), temp);
            System.out.println("Suma dla temp: " + temp + " dla wszystkich miesiecy wynosi: " + temperatureSum);
        }
        return temperatureSum;
    }

   // zwraca mape z temperatura od -20 do 30 gdzie dla kazdej tej temp jest podsumowana ilosc godzin w calym roku kiedy ona wystepuje

    public HashMap<Integer, Double> getYearTemperaturesforChart() throws IOException
    {

        HashMap<Integer, Double> yearAmountTemperature = new HashMap<>();

            for(int i=-20; i < 30; i++) {
                yearAmountTemperature.put(i, getCountOfYearTempHours((double) i));
            }

            return yearAmountTemperature;
    }
}


