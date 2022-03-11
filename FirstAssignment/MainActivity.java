package SO_SAD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// THE TEAM : Adel Krylova - VLADIMIR ZELENOKOR - MADE OKA RESIA WEDAMERTA - AYHEM BOUABID
// THE DATE : 07/10/2021
// THIS IS OUR SOLUTION OF THE FIRST SSAD HOMEWORK PROJECT. OUR TASK IS NUMBER 21.
public class MainActivity {
    private static final List <Patient> PATIENTS = new ArrayList<>();
    private static final Random GENERATOR = new Random();
    private static final int PATIENTS_NUMBER = 2;

    public static void activityOnCreate() {
        PATIENTS.add(new Patient());
    }

    public static void activityOnVisit(Patient patient) {
        patient.askForPrediction();
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < PATIENTS_NUMBER; i++) {
            activityOnCreate();
        }
        activityOnVisit(PATIENTS.get(GENERATOR.nextInt(PATIENTS_NUMBER)));
    }
}
