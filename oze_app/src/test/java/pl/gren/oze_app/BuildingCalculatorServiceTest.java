package pl.gren.oze_app;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.gren.oze_app.model.BuildingConstants;
import pl.gren.oze_app.model.BuildingRequirements;
import pl.gren.oze_app.model.EnergyData;
import pl.gren.oze_app.repository.BuildingRequirementsRepository;
import pl.gren.oze_app.service.BuildingCalculatorService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BuildingCalculatorServiceTest {

    @Mock
    private BuildingRequirementsRepository buildingRequirementsRepository;

    @InjectMocks
    private BuildingCalculatorService buildingCalculatorService;

    @Test
    public void testLost1() {
        // Utwórz obiekt BuildingRequirements z przykładowymi danymi testowymi
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setBuildingType("singleStory");
        testBuildingRequirements.setBuildingDimensionsX(10.0);
        testBuildingRequirements.setBuildingDimensionsY(7.0);
        testBuildingRequirements.setRoomHeight(2.5);
        testBuildingRequirements.setRoomHeatingTemperature(23.0);
        testBuildingRequirements.setLocationTemperature(-20.0);
        testBuildingRequirements.setAf47(0.0); // Możesz ustawić wartość początkową, jeśli to konieczne
        testBuildingRequirements.setLost1(0.0);
        testBuildingRequirements.setWallType("ceramicBrick");
        testBuildingRequirements.setWallThickness(30.0);
        testBuildingRequirements.setWallInsulation("styrofoam");
        testBuildingRequirements.setWallInsulationThickness(10.0);
        testBuildingRequirements.setBasementPresent(true);

        // Ustal, jak ma się zachować repository podczas wywołań
        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));
        when(buildingRequirementsRepository.save(any())).thenReturn(testBuildingRequirements);


        // Wywołaj metodę, którą chcesz przetestować
        buildingCalculatorService.lost1(1L); // Tu podaj prawidłowy ID

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));

        // Użyj SoftAssertions do sprawdzenia wszystkich warunków
        SoftAssertions softly = new SoftAssertions();


        /// BIG DECIMAL LEPSZY
        // Sprawdź, czy wyniki są zgodne z oczekiwaniami
        double expectedAf47 = calculateExpectedAf47(testBuildingRequirements);
        assertEquals(expectedAf47, updatedBuildingRequirements.getAf47(), 1e-6);

        double expectedLost1Math = calculateExpectedLost1Math(testBuildingRequirements);
        assertEquals(expectedLost1Math, updatedBuildingRequirements.getLost1(), 0);


        // Wywołaj assertAll(), aby sprawdzić wszystkie warunki
        softly.assertAll();


        //  BigDecimal.valueOf(af47);

        // Sprawdź, czy metoda repository została poprawnie wywołana
        verify(buildingRequirementsRepository, times(2)).findById(anyLong());
        verify(buildingRequirementsRepository, times(1)).save(any());
    }

    private double calculateExpectedAf47(BuildingRequirements buildingRequirements) {
        double wallTypeMultiplier = BuildingConstants.WALL_TYPE_VALUES.get(buildingRequirements.getWallType());
        double wallInsulationMultiplier = BuildingConstants.ISOLATION_VALUES.get(buildingRequirements.getWallInsulation());

        return 1 / (((buildingRequirements.getWallThickness() / 100) / wallTypeMultiplier) +
                (buildingRequirements.getWallInsulationThickness() / 100) / wallInsulationMultiplier);
    }

    private double calculateExpectedLost1Math(BuildingRequirements buildingRequirements) {
        double buildingTypeMultiplier1 = BuildingConstants.BUILDING_TYPE_MULTIPLIERS1.get(buildingRequirements.getBuildingType());

        return Math.round((buildingRequirements.getBuildingDimensionsX() * 2 + buildingRequirements.getBuildingDimensionsY() * 2) *
                buildingRequirements.getRoomHeight() * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()) *
                buildingRequirements.getAf47() * (buildingTypeMultiplier1 + (buildingRequirements.isBasementPresent() ? 0.5 : 0)));
    }


    @Test
    public void testLost2() {
        // Utwórz obiekt BuildingRequirements z przykładowymi danymi testowymi
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setBuildingType("singleStory");
        testBuildingRequirements.setBuildingDimensionsSum(70.0);
        testBuildingRequirements.setRoomHeatingTemperature(23.0);
        testBuildingRequirements.setWallInsulationThickness(10);
        testBuildingRequirements.setRoofInsulationThickness(25);
        testBuildingRequirements.setLocationTemperature(-20.0);
        testBuildingRequirements.setRoofInsulation("polyurethaneFoam");
        testBuildingRequirements.setAf48(0.0); // Możesz ustawić wartość początkową, jeśli to konieczne
        testBuildingRequirements.setLost2(0.0);

        // Ustal, jak ma się zachować repository podczas wywołań
        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        // Wywołaj metodę, którą chcesz przetestować
        buildingCalculatorService.lost2(1L); // Tu podaj prawidłowy ID

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));

        // Użyj SoftAssertions do sprawdzenia wszystkich warunków
        SoftAssertions softly = new SoftAssertions();

        // Sprawdź, czy wyniki są zgodne z oczekiwaniami
        double expectedAf48 = calculateExpectedAf48(testBuildingRequirements);
        assertEquals(expectedAf48, updatedBuildingRequirements.getAf48(), 1e-6);

        double expectedLost2 = calculateExpectedLost2(testBuildingRequirements);
        assertEquals(expectedLost2, updatedBuildingRequirements.getLost2(), 0);


        // Wywołaj assertAll(), aby sprawdzić wszystkie warunki
        softly.assertAll();

        // Sprawdź, czy metoda repository została poprawnie wywołana
        verify(buildingRequirementsRepository, times(2)).findById(anyLong());
    }

    private double calculateExpectedAf48(BuildingRequirements buildingRequirements) {
        double roofInsulationMultiplier = BuildingConstants.ISOLATION_VALUES.get(buildingRequirements.getRoofInsulation());

        return 1 / ((buildingRequirements.getRoofInsulationThickness() / 100) / roofInsulationMultiplier);
    }

    private double calculateExpectedLost2(BuildingRequirements buildingRequirements) {
        double buildingTypeMultiplier2 = BuildingConstants.BUILDING_TYPE_MULTIPLIERS2.get(buildingRequirements.getBuildingType());

        return Math.round(buildingRequirements.getBuildingDimensionsSum() * buildingTypeMultiplier2 *
                buildingRequirements.getAf48() * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()));
    }


    @Test
    public void testLost3() {
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setBuildingType("singleStory");
        testBuildingRequirements.setBuildingDimensionsSum(70.0);
        testBuildingRequirements.setRoomHeatingTemperature(23.0);
        testBuildingRequirements.setBuildingDimensionsX(10.0);
        testBuildingRequirements.setBuildingDimensionsY(7.0);
        testBuildingRequirements.setWallThickness(30.0);
        testBuildingRequirements.setWallInsulation("styrofoam");
        testBuildingRequirements.setWallInsulationThickness(10.0);
        testBuildingRequirements.setFloorInsulation("styrofoam");
        testBuildingRequirements.setFloorInsulationThickness(10.0);
        testBuildingRequirements.setLocationTemperature(-20.0);
        testBuildingRequirements.setBasementPresent(true);

        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        buildingCalculatorService.lost3(1L);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));

        SoftAssertions softly = new SoftAssertions();

        System.out.println("Actual af51: " + updatedBuildingRequirements.getAf51());
        System.out.println("Actual ah51: " + updatedBuildingRequirements.getAh51());
        System.out.println("BASEMENT: " + updatedBuildingRequirements.isBasementPresent());
        System.out.println("Actual aj51: " + updatedBuildingRequirements.getAj51());
        System.out.println("Actual lost3: " + updatedBuildingRequirements.getLost3());

        double expectedAf51 = calculateExpectedAf51(testBuildingRequirements);
        assertEquals(expectedAf51, updatedBuildingRequirements.getAf51(), 1e-3);

        double expectedAh51 = calculateExpectedAh51(testBuildingRequirements);
        assertEquals(expectedAh51, updatedBuildingRequirements.getAh51(), 1e-3);

        double expectedAj51 = calculateExpectedAj51(testBuildingRequirements, expectedAf51);
        assertEquals(expectedAj51, updatedBuildingRequirements.getAj51(), 1e-3);

        double expectedLost3 = calculateExpectedLost3(testBuildingRequirements, expectedAh51, expectedAj51);
        assertEquals(expectedLost3, updatedBuildingRequirements.getLost3(), 1e-3);

        softly.assertAll();

        verify(buildingRequirementsRepository, times(2)).findById(anyLong());
    }

    private double calculateExpectedAf51(BuildingRequirements buildingRequirements) {
        double floorInsulationMultiplier = BuildingConstants.ISOLATION_VALUES.get(buildingRequirements.getFloorInsulation());

        return 1 / ((buildingRequirements.getFloorInsulationThickness() / 100.0) / floorInsulationMultiplier);
    }

    public static double roundUpToEven(double value) {
        double roundedValue = Math.ceil(value);
        return (roundedValue % 2 == 0) ? roundedValue : roundedValue + 1;
    }

    private double calculateExpectedAh51(BuildingRequirements buildingRequirements) {

        return roundUpToEven(buildingRequirements.getBuildingDimensionsSum() /
                (0.5 * (buildingRequirements.getBuildingDimensionsX() * 2 + buildingRequirements.getBuildingDimensionsY() * 2 -
                        (buildingRequirements.getWallThickness() + buildingRequirements.getWallInsulationThickness()) / 100 * 4)));
    }

    private double calculateExpectedAj51(BuildingRequirements buildingRequirements, double expectedAf51) {


        double ah51 = buildingRequirements.isBasementPresent() ?
                findAH51((int) calculateExpectedAh51(buildingRequirements), (int) findAF51(expectedAf51), true) :
                findAH51((int) calculateExpectedAh51(buildingRequirements), (int) findAF51(expectedAf51), false);


        return 1.45 * ((buildingRequirements.getRoomHeatingTemperature() - 7.6) /
                (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature())) *
                buildingRequirements.getBuildingDimensionsSum() *
                (buildingRequirements.getBuildingDimensionsSum() /
                        (0.5 * (buildingRequirements.getBuildingDimensionsX() * 2 + buildingRequirements.getBuildingDimensionsY() * 2))) *
                ah51 * 1.9;
    }

    private double calculateExpectedLost3(BuildingRequirements buildingRequirements, double expectedAh51, double expectedAj51) {
        return expectedAj51;
    }

    public double findAH51(int ah51, int position, boolean flag) {
        double value;

        if (flag) {
            if (BuildingConstants.BASEMENT_FLOOR_VALUES.containsKey(ah51)) {
                System.out.println("Je żech tu!");
                value = BuildingConstants.BASEMENT_FLOOR_VALUES.get(ah51).get(position);
            } else {
                value = 0;
            }
        } else {
            if (BuildingConstants.BASEMENT_FLOOR_VALUES_WITHOUT_INSULATION.containsKey(ah51)) {
                value = BuildingConstants.BASEMENT_FLOOR_VALUES_WITHOUT_INSULATION.get(ah51).get(position);
            } else {
                value = 0;
            }
        }

        return value;
    }

    public double findAF51(double af51) {

        ArrayList<Double> rowValues = new ArrayList<>();
        Map<Integer, Double> innerMap = BuildingConstants.BASEMENT_FLOOR_VALUES.get(0);


        // Jeśli istnieje taka mapa
        if (innerMap != null) {
            // Pętla po wartościach wewnętrznej mapy (klucze Integer i wartości Double)
            for (Double value : innerMap.values()) {
                rowValues.add(value);
                System.out.println("Wartosci rowValues w findaf51: " + value);
            }
        }

        Collections.reverse(rowValues);

        for (Double value : rowValues) {
            if (value > af51) {
                System.out.println("Find AF51: ");
                System.out.println("Found value greater than af51: " + value);

                return rowValues.indexOf(value)+1;
            }
        }

        System.out.println("No value greater than af51 found, returning the last value.");
        return 0;
    }




    @Test
    public void testLost4() {
        // Utwórz obiekt BuildingRequirements z przykładowymi danymi testowymi
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setWindowBlindType("doubleGlazed");
        testBuildingRequirements.setWindowBlindAmount(6);
        testBuildingRequirements.setRoomHeatingTemperature(23.0);
        testBuildingRequirements.setLocationTemperature(-20.0);


        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService(buildingRequirementsRepository);

        buildingCalculatorService.lost4(1L);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));

        // Użyj SoftAssertions do sprawdzenia wszystkich warunków
        SoftAssertions softly = new SoftAssertions();

        // Sprawdź, czy wyniki są zgodne z oczekiwaniami
        double expectedAf49 = calculateExpectedAf49(testBuildingRequirements);
        System.out.println("Expected49: " + expectedAf49);
        assertEquals(expectedAf49, updatedBuildingRequirements.getAf49(), 1e-6);

        double expectedLost4 = calculateExpectedLost4(testBuildingRequirements);
        System.out.println("expectedLost4: " + expectedLost4);

        assertEquals(expectedLost4, updatedBuildingRequirements.getLost4(), 0);

        // Wywołaj assertAll(), aby sprawdzić wszystkie warunki
        softly.assertAll();

        // Sprawdź, czy metoda repository została poprawnie wywołana
        verify(buildingRequirementsRepository, times(2)).findById(anyLong());
    }

    private double calculateExpectedAf49(BuildingRequirements buildingRequirements) {
        String windowBlindType = buildingRequirements.getWindowBlindType();
        return "doubleGlazed".equals(windowBlindType) ? 1.2 : ("tripleGlazed".equals(windowBlindType) ? 0.63 : 0);
    }

    private double calculateExpectedLost4(BuildingRequirements buildingRequirements) {
        double af49 = calculateExpectedAf49(buildingRequirements);
        return Math.round(1.5 * 1.5 * buildingRequirements.getWindowBlindAmount() * af49 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()));
    }


    @Test
    public void testLost5()
    {

        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setEntranceDoorsAmount(1);
        testBuildingRequirements.setLocationTemperature(-20);
        testBuildingRequirements.setRoomHeatingTemperature(23);

        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService(buildingRequirementsRepository);

        buildingCalculatorService.lost5(1L);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));

        double expectedLost5 = calculateExpectedLost5(testBuildingRequirements);
        System.out.println("expectedLost5: " + expectedLost5);

        assertEquals(expectedLost5, updatedBuildingRequirements.getLost5(), 0);

        // Sprawdź, czy metoda repository została poprawnie wywołana
        verify(buildingRequirementsRepository, times(2)).findById(anyLong());
    }

    private double calculateExpectedLost5(BuildingRequirements buildingRequirements) {

        return Math.round(2 * 1 * buildingRequirements.getEntranceDoorsAmount() * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()));
    }

    @Test
    public void testLost6() {
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setVentilationType("mechanical");
        testBuildingRequirements.setHeatedArea(110.0);
        testBuildingRequirements.setRoomHeight(2.3);
        testBuildingRequirements.setEntranceDoorsAmount(1);
        testBuildingRequirements.setBuildingType("singleStoryWithUnusedAttic");
        testBuildingRequirements.setRoomHeatingTemperature(22.0);
        testBuildingRequirements.setLocationTemperature(-20.0);

        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService(buildingRequirementsRepository);

        buildingCalculatorService.lost6(1L);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));


        // Użyj SoftAssertions do sprawdzenia wszystkich warunków
        SoftAssertions softly = new SoftAssertions();

        // Sprawdź, czy wyniki są zgodne z oczekiwaniami
        double expectedAg52 = calculateExpectedAg52(testBuildingRequirements);
        double expectedAh58 = calculateExpectedAh58(testBuildingRequirements);
        double expectedAi55 = calculateExpectedAi55(testBuildingRequirements);
        double expectedAj58 = calculateExpectedAj58(testBuildingRequirements);
        double expectedAj55 = calculateExpectedAj55(testBuildingRequirements);
        double expectedLost6 = calculateExpectedLost6(testBuildingRequirements);

        softly.assertThat(updatedBuildingRequirements.getAg52()).isEqualTo(expectedAg52);
        System.out.println("Ag52: Expected=" + expectedAg52 + ", Actual=" + updatedBuildingRequirements.getAg52());

        softly.assertThat(updatedBuildingRequirements.getAh58()).isEqualTo(expectedAh58);
        System.out.println("Ah58: Expected=" + expectedAh58 + ", Actual=" + updatedBuildingRequirements.getAh58());

        softly.assertThat(updatedBuildingRequirements.getAi55()).isEqualTo(expectedAi55);
        System.out.println("Ai55: Expected=" + expectedAi55 + ", Actual=" + updatedBuildingRequirements.getAi55());

        softly.assertThat(updatedBuildingRequirements.getAj58()).isEqualTo(expectedAj58);
        System.out.println("Aj58: Expected=" + expectedAj58 + ", Actual=" + updatedBuildingRequirements.getAj58());

        softly.assertThat(updatedBuildingRequirements.getAj55()).isEqualTo(expectedAj55);
        System.out.println("Aj55: Expected=" + expectedAj55 + ", Actual=" + updatedBuildingRequirements.getAj55());

        softly.assertThat(updatedBuildingRequirements.getLost6()).isEqualTo(expectedLost6);
        System.out.println("Lost6: Expected=" + expectedLost6 + ", Actual=" + updatedBuildingRequirements.getLost6());


        // Wywołaj assertAll(), aby sprawdzić wszystkie warunki
        softly.assertAll();

        // Sprawdź, czy metoda repository została poprawnie wywołana
        verify(buildingRequirementsRepository, times(2)).findById(anyLong());

    }

    private double calculateExpectedAg52(BuildingRequirements buildingRequirements) {
        double entranceDoorAmount = buildingRequirements.getEntranceDoorsAmount();
        double buildingTypeMultiplier1 = BuildingConstants.BUILDING_TYPE_MULTIPLIERS1.get(buildingRequirements.getBuildingType());
        return Math.round(2 * buildingRequirements.getHeatedArea() * buildingRequirements.getRoomHeight() * buildingTypeMultiplier1 * 3.5 * (entranceDoorAmount >= 2 ? 0.03 : 0.02) * 1 * 100.0) / 100.0;
    }

    private double calculateExpectedAh58(BuildingRequirements buildingRequirements) {
        return Math.round((20 - (buildingRequirements.getLocationTemperature() + 0.4 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()))) / (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()) * 100.0) / 100.0;
    }

    private double calculateExpectedAi55(BuildingRequirements buildingRequirements) {
        double ag52 = calculateExpectedAg52(buildingRequirements);
        double ah58 = calculateExpectedAh58(buildingRequirements);
        return Math.round((ag52 + 35 * ah58 + 0) * 100.0) / 100.0;
    }

    private double calculateExpectedAj58(BuildingRequirements buildingRequirements) {
        return Math.round((20 - (buildingRequirements.getLocationTemperature() + 0.8 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature())) / (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature())) * 100.0) / 100.0;
    }

    private double calculateExpectedAj55(BuildingRequirements buildingRequirements) {
        double ag52 = calculateExpectedAg52(buildingRequirements);
        double aj58 = calculateExpectedAj58(buildingRequirements);
        return Math.round((ag52 + 20 * aj58 + 0) * 100.0) / 100.0;
    }

    private double calculateExpectedLost6(BuildingRequirements buildingRequirements) {

        String ventilationType = buildingRequirements.getVentilationType();
        double ag52 = calculateExpectedAg52(buildingRequirements);

        double value;

        if (ventilationType.equals("gravity")) {
            value = Math.round((0.34 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature()) * 0.5 * buildingRequirements.getHeatedArea() * buildingRequirements.getRoomHeight() * 1.2) * 100.0) / 100.0;
        } else if (ventilationType.equals("mechanical")) {
            double ah58 = calculateExpectedAh58(buildingRequirements);
            double ai55 = Math.round((ag52 + 35 * ah58 + 0) * 100.0) / 100.0;
            value = Math.round((0.34 * ai55 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature())) * 100.0) / 100.0;
        } else if (ventilationType.equals("mechanicalWithHeatRecovery")) {
            double aj58 = calculateExpectedAj58(buildingRequirements);
            double aj55 = Math.round((ag52 + 20 * aj58 + 0) * 100.0) / 100.0;
            value = Math.round((0.34 * aj55 * (buildingRequirements.getRoomHeatingTemperature() - buildingRequirements.getLocationTemperature())) * 100.0) / 100.0;
        } else {
            value = 0;
        }

        return value;
    }

    @Test
    public void testCalculateFuel() {
        // Ustaw dane testowe
        BuildingRequirements testBuildingRequirements = new BuildingRequirements();
        testBuildingRequirements.setId(1L);
        testBuildingRequirements.setFuelUsageAmount(6000);
        testBuildingRequirements.setFuelEnergyValue(4.9);
        testBuildingRequirements.setHeatingSourceEfficiency(0.85);


        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuildingRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService(buildingRequirementsRepository);

        double result = buildingCalculatorService.calculateFuel(testBuildingRequirements);
        System.out.println("result: " + result);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuildingRequirements.getId())
                .orElseThrow(() -> new RuntimeException("BuildingRequirements not found for id: " + testBuildingRequirements.getId()));


        // Sprawdź, czy wynik jest zgodny z oczekiwaniami
        assertEquals(6000.0 * 6.4 * 0.75 / 2000, result, 0.001);
    }


    @Test
    public void testCOValue() {

        BuildingRequirements testBuilidngRequirements = new BuildingRequirements();
        testBuilidngRequirements.setId(1L);
        testBuilidngRequirements.setLost1(1516);
        testBuilidngRequirements.setLost2(305);
        testBuilidngRequirements.setLost3(71);
        testBuilidngRequirements.setLost4(697);
        testBuilidngRequirements.setLost5(86);
        testBuilidngRequirements.setLost6(2412);

        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuilidngRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService((buildingRequirementsRepository));

        double result = buildingCalculatorService.calculateCO(testBuilidngRequirements);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuilidngRequirements.getId())
                .orElseThrow(() -> new RuntimeException(("BuildingRequirements not found for id: " + testBuilidngRequirements.getId())));


                double CoValue = (testBuilidngRequirements.getLost1() + testBuilidngRequirements.getLost2() + testBuilidngRequirements.getLost3() + testBuilidngRequirements.getLost4() + testBuilidngRequirements.getLost5() + testBuilidngRequirements.getLost6()) / 2000;


                assertEquals(CoValue, result);

    }

    @Test
    public void testCWUValue() {

        BuildingRequirements testBuilidngRequirements = new BuildingRequirements();
        testBuilidngRequirements.setId(1L);
        testBuilidngRequirements.setWaterDemand(200);
        testBuilidngRequirements.setPeopleCountWater(4);

        when(buildingRequirementsRepository.findById(anyLong())).thenReturn(Optional.of(testBuilidngRequirements));

        BuildingCalculatorService buildingCalculatorService = new BuildingCalculatorService(buildingRequirementsRepository);

        double result = buildingCalculatorService.calculateCWU(testBuilidngRequirements);

        BuildingRequirements updatedBuildingRequirements = buildingRequirementsRepository.findById(testBuilidngRequirements.getId())
                .orElseThrow(() -> new RuntimeException(("BuildingRequirements not found for id: " + testBuilidngRequirements.getId())));


        double CWUValue = 200 / 4.0;

        assertEquals(CWUValue, result);

    }

//    @Test
//    public void testHeatingValue() {
//        // Mocking the BuildingRequirements object
//        BuildingRequirements buildingRequirements = mock(BuildingRequirements.class);
//        when(buildingRequirements.getHeatingPumpSetTemperature()).thenReturn(25.0); // Example value
//        when(buildingRequirements.getCOValue()).thenReturn(50.0); // Example value
//
//        // Creating an instance of the EnergyData class
//        EnergyData energyData = new EnergyData(buildingRequirements, h);
//
//        // Testing for temperature <= -17
//        double temperature1 = -18;
//        double expectedHeatingValue1 = 50.0; // Since temperature is <= -17, it should return heatingCO
//        assertEquals(expectedHeatingValue1, energyData.getHeatingValue(temperature1), 0.01);
//
//        // Testing for heatPumpOnTemperature >= temperature
//        double temperature2 = 20;
//        double expectedHeatingValue2 = Math.round((50.0 - (50.0 / 17 + 25.0 + 1) * (17 - (temperature2 * -1))) * 100.0) / 100.0;
//        assertEquals(expectedHeatingValue2, energyData.getHeatingValue(temperature2), 0.01);
//
//        // Testing for heatPumpOnTemperature < temperature
//        double temperature3 = 10;
//        double expectedHeatingValue3 = 0.0; // Since heatPumpOnTemperature < temperature, it should return 0
//        assertEquals(expectedHeatingValue3, energyData.getHeatingValue(temperature3), 0.01);
//    }

    }











