package SO_SAD;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private final API PERSONAL_API;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String ADDRESS;
    private int AGE;
    private Gender SEX;

    List<Symptom> currentSymptoms;
    List<Disease> medicalHistory;

    // basic constructor
    public Patient(String name, String LAST_NAME, String ADDRESS, int AGE, String SEX) {
        this.PERSONAL_API = API.getInstance();
        this.FIRST_NAME = name;
        this.LAST_NAME = LAST_NAME;
        this.ADDRESS = ADDRESS;
        this.AGE = AGE;
        this.SEX = Gender.getGender(SEX);
        currentSymptoms = new ArrayList<>();
        medicalHistory = new ArrayList<>();
    }

    // an auxiliary constructor: used in Patient() constructor
    private Patient(String[] data) {
        this(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
    }

    // constructor used to ensure the creating of the patient object through input while maintaining
    // the user experience provided by Communication class
    public Patient() {
        this(Communication.register());
    }

    /*
        setters and getters for all class fields: encapsulation principle
     */
    public void setFirstName(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLastName() {
        return LAST_NAME;
    }

    public void setLastName(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public List<Symptom> getCurrentSymptoms() {
        return currentSymptoms;
    }

    public void setCurrentSymptoms(List<Symptom> currentSymptoms) {
        this.currentSymptoms = currentSymptoms;
    }

    public String getAddress() {
        return ADDRESS;
    }

    public void setAddress(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public int getAge() {
        return AGE;
    }

    public void setAge(int AGE) {
        this.AGE = AGE;
    }

    public Gender getSex() {
        return SEX;
    }

    public void setSex(String SEX) {
        this.SEX =Gender.getGender(SEX);
    }

    /**
     * This method sets the currentSymptoms field
     * According to the symptoms, the PERSONAL_API field determine the most probable illness
     * and find the nearest doctor while outputting both of them.
     */
    public void askForPrediction() {
        setCurrentSymptoms(Communication.askForSymptoms());
        medicalHistory.add(Communication.displayPrediction(PERSONAL_API.predict(this.getCurrentSymptoms())));
        Communication.displayDoctor(PERSONAL_API.findDoctor(this.getAddress()));
        currentSymptoms.clear();
    }

    @Override
    public String toString() {
        return FIRST_NAME + " " + LAST_NAME + " " + SEX.toString() + " " + AGE + " years old " + "resident at " + ADDRESS;
    }

}
