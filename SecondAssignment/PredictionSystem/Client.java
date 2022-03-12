package sosad;

import sosadmemberships.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String ADDRESS;
    private int AGE;
    private Gender SEX;
    private final ServiceProvider serviceProvider;
    private List<Symptom> currentSymptoms;
    private final List<Disease> medicalHistory;

    // basic constructor
    public Client(String name, String LAST_NAME, String ADDRESS, int AGE, String SEX, String membership) {
        this.FIRST_NAME = name;
        this.LAST_NAME = LAST_NAME;
        this.ADDRESS = ADDRESS;
        this.AGE = AGE;
        this.SEX = Gender.getGender(SEX);
        this.serviceProvider = getService(Membership.getValue(membership));
        this.currentSymptoms = new ArrayList<>();
        this.medicalHistory = new ArrayList<>();
    }

    // an auxiliary constructor: used in Client() constructor
    private Client(String[] data) {
        this(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4],data[5]);
    }

    // constructor used to ensure the creating of the patient object through input while maintaining
    // the user experience provided by Communication class
    public Client() {
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
        this.SEX = Gender.getGender(SEX);
    }

    public void addDiseaseToMedicalHistory(Disease d){
        this.medicalHistory.add(d);
    }

    public List<Disease> getMedicalHistory() {
        return medicalHistory;
    }

    // set the correspondent serviceProvider according to the membership
    private ServiceProvider getService(Membership membership){
        return switch (membership) {
            case BASIC -> new BasicServiceProvider();
            case PRO -> new ProServiceProvider();
            case PRO_PLUS -> new ProPlusServiceProvider();
            default -> new PremiumServiceProvider();
        };
    }

    // this method will provide the service to the user by calling the serviceProvider field
    public void askForService(){
        serviceProvider.provideService(this);
    }

    // a custom representation of the Client object
    @Override
    public String toString() {
        return FIRST_NAME + " " + LAST_NAME + " " + SEX.toString() + " " + AGE + " years old " + "resident at " + ADDRESS;
    }

}
