package pl.gren.oze_app.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CSVReader {

    private final String filePath = "Dane_meteo.csv";

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

        temperatureSum += countHoursWithTemperature("styczeń", temp);
        temperatureSum += countHoursWithTemperature("luty", temp);
        temperatureSum += countHoursWithTemperature("marzec", temp);
        temperatureSum += countHoursWithTemperature("kwiecień", temp);
        temperatureSum += countHoursWithTemperature("maj", temp);
        temperatureSum += countHoursWithTemperature("czerwiec", temp);
        temperatureSum += countHoursWithTemperature("lipiec", temp);
        temperatureSum += countHoursWithTemperature("sierpień", temp);
        temperatureSum += countHoursWithTemperature("wrzesień", temp);
        temperatureSum += countHoursWithTemperature("październik", temp);
        temperatureSum += countHoursWithTemperature("listopad", temp);
        temperatureSum += countHoursWithTemperature("grudzień", temp);

        System.out.println("Suma dla temp: " + temp + " dla wszystkich miesiecy wynosi: " + temperatureSum);

        return temperatureSum;
    }


}


