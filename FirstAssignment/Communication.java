package SO_SAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Communication {
    // these variables determine the interaction of the interface ( the terminal in our mini project ) with the user
    // any change in the interaction process should be performed only in one point: the corresponding static variable

    //welcoming sentence to the newly-added patient
    private final static String WELCOME = "Welcome to our application. Thank you for trusting us";

    // first and last name interaction text
    private final static String ASK_FOR_FIRST_NAME = "Would you please enter your first name";
    private final static String ASK_FOR_LAST_NAME = "Would you please enter your last name";

    // address interaction text
    private final static String ASK_FOR_ADDRESS = "Would you please enter your address";
    private final static String ANNOUNCE_ADDRESS_PATTERN = "Please make sure the address : CityName HorizontalAvenue VerticalAvenue";
    private final static String ANNOUNCE_WRONG_ADDRESS = "We are sorry to let you know that the address is invalid";
    private final static String ASK_FOR_ADDRESS_AGAIN = "Please have the kindness to re-enter you address according to the pattern";
    private final static String ASK_FOR_AGE = "Would you please enter your age";

    // age settings as well as interaction text
    private final static int MIN_AGE = 0;
    private final static int MAX_AGE = 140;
    private final static String ASK_FOR_NUMERICAL_STRING = "Please make sure to enter a numerical value\n"
            +"larger than " + MIN_AGE + " and smaller than " + MAX_AGE;
    private final static String ANNOUNCE_INVALID_AGE = "We are sorry to let you know that the age entered is Invalid";

    // gender interaction text
    private final static String ASK_FOR_GENDER = "Would you please enter your gender : MALE or FEMALE";

    // a message to announce successful registration
    private final static String REGISTRATION_COMPLETE = "Your account has been added successfully";

    // this variable stores the string that ends the INPUT when taking symptoms
    private final static String END_INPUT = "END";

    // symptoms interaction text
    private final static String ASK_FOR_SYMPTOMS = "Please enter all of your symptoms and enter " + END_INPUT + " at the end.";

    // disease interaction text
    private final static String DISEASE_ANNOUNCEMENT = "According to the provided symptoms and our data. " +
            "The most probable disease is ";
    private final static String NO_DISEASE_MATCH = "We are terribly sorry to let you know that none of the symptoms " +
            "passed match any of our diseases' symptoms.";

    // doctor checking interaction text
    private final static String DOCTOR_CHECK = "Please seek professional treatment by the nearest doctor: ";
    private final static String DEFAULT_DOCTOR = "We are terribly sorry to let you know that your current city has " +
            "not been added to our database yet.";
    private final static String DEFAULT_DOCTOR_CHECK = "Please seek professional treatment by one of our best doctors: ";

    // static Scanner used for INPUT:
    private final static Scanner INPUT = new Scanner(System.in);

    // this method makes sure that the address entered should be valid (according to the model specified)
    // while taking into consideration the user experience
    static String validateAddress() {
        System.out.println(ASK_FOR_ADDRESS);
        System.out.println(ANNOUNCE_ADDRESS_PATTERN);

        String address = INPUT.nextLine();

        while (!AddressHandler.isValidAddress(address)) {
            System.out.println(ANNOUNCE_WRONG_ADDRESS);
            System.out.println(ASK_FOR_ADDRESS_AGAIN);
            address = INPUT.nextLine();
        }
        return AddressHandler.legitimateAddress(address);
    }

    // this method makes sure the value entered by the user is indeed a numerical value
    // as well as a value medically relevant (no patient is 1400 years old)
    private static String validateAge() {
        System.out.println(ASK_FOR_AGE);
        System.out.println(ASK_FOR_NUMERICAL_STRING);
        String age = INPUT.nextLine();

        while (true) {
            try {
                int realAge = Integer.parseInt(age);
                if ( realAge > MIN_AGE && realAge < MAX_AGE)
                    break;
            }
            catch(NumberFormatException parseExp) {
             // control passed to the catch block means that the string entered is not numerical
            }

            System.out.println(ANNOUNCE_INVALID_AGE);
            System.out.println(ASK_FOR_NUMERICAL_STRING);
            age = INPUT.nextLine();
        }
        return age;
    }

    // validate the gender of the patient : Male or Female
    private static String validateSex() {
        System.out.println(ASK_FOR_GENDER);
        String gender = INPUT.nextLine();
        String g = gender.trim().replaceAll("\\s", "");

        while (!(g.equalsIgnoreCase("MALE") || g.equals("FEMALE"))) {
            System.out.println(ASK_FOR_GENDER);
            gender = INPUT.nextLine();
            g = gender.trim().replaceAll("\\s", "");
        }
        return g.toUpperCase();
    }

    // gathering the patient's information while taking into consideration the user-experience
    public static String[] register() {
        System.out.println(WELCOME);

        System.out.println(ASK_FOR_FIRST_NAME);
        String firstName = INPUT.nextLine();

        System.out.println(ASK_FOR_LAST_NAME);
        String lastName = INPUT.nextLine();

        String address = validateAddress();

        String age = validateAge();

        String sex = validateSex();

        System.out.println(REGISTRATION_COMPLETE);
        System.out.println();

        return new String[] {firstName,lastName,address,age,sex};
    }

    public static Patient register(String firstName, String lastName, String address, int age, String gender) {
        return new Patient(firstName,lastName,address,age,gender);
    }

    // gathering the patient's symptoms while taking into consideration the user-experience
    public static ArrayList<Symptom> askForSymptoms() {
        System.out.println(ASK_FOR_SYMPTOMS);
        String symptom = INPUT.nextLine();
        ArrayList<Symptom> s = new ArrayList<>();

        while (!symptom.equalsIgnoreCase(END_INPUT)) {
            s.add(new Symptom(symptom));
            symptom = INPUT.nextLine();
        }
        return s;
    }

    // display the passed disease
    public static Disease displayPrediction(Disease disease) {
        if (disease != null) {
            System.out.println(DISEASE_ANNOUNCEMENT+disease.getName());
        }
        else{
            System.out.println(NO_DISEASE_MATCH);
        }
        System.out.println();
        return disease;
    }

    // display the nearest doctor according to the passed address
    // or announce that the patient's city is not on the list
    public static Doctor displayDoctor(Doctor doctor) {
        if (doctor.isMatchPatientCity()) {
            System.out.println(DOCTOR_CHECK);
        }
        else{
            System.out.println(DEFAULT_DOCTOR);
            System.out.println(DEFAULT_DOCTOR_CHECK);
        }
        System.out.println(doctor);
        System.out.println();
        return doctor;
    }
}