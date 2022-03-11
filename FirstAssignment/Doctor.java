package SO_SAD;


public class Doctor {
    private String name;
    private String address;

    // this field is used to check whether the doctor's city is the same as the patient's
    private boolean matchPatientCity ;
    
    // constructor
    public Doctor (String name, String address) {
        this.name = name;
        this.address = address;
        matchPatientCity = true;
    }

    // getters and setters : encapsulation principle
    public String getAddress() {return address;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMatchPatientCity() {
        return matchPatientCity;
    }

    public void setMatchPatientCity(boolean matchPatientCity) {
        this.matchPatientCity = matchPatientCity;
    }

    // customized display of the Doctor object
    @Override
    public String toString() {
        return "Doctor " + getName() + " working at " + getAddress();
    }
}