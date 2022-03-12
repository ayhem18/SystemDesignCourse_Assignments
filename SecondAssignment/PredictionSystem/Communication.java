package sosad;

import sosadmemberships.InsuranceCompany;
import sosadmemberships.Membership;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Communication {
  // these variables determine the interaction of the interface ( the terminal in our mini project )
  // with the user
  // any change in the interaction process should be performed only in one point: the corresponding
  // static variable

  // a welcome sentence to the newly-added patient
  private static final String WELCOME = "Welcome to our application. Thank you for trusting us";

  // first and last name interaction text
  private static final String ASK_FOR_FIRST_NAME = "Would you please enter your first name";
  private static final String ASK_FOR_LAST_NAME = "Would you please enter your last name";

  // address interaction text
  private static final String ASK_FOR_ADDRESS = "Would you please enter your address";
  private static final String ANNOUNCE_ADDRESS_PATTERN =
      "Please make sure the address : CityName HorizontalAvenue VerticalAvenue";
  private static final String ANNOUNCE_WRONG_ADDRESS =
      "We are sorry to let you know that the address is invalid";
  private static final String ASK_FOR_ADDRESS_AGAIN =
      "Please have the kindness to re-enter you address according to the pattern";
  private static final String ASK_FOR_AGE = "Would you please enter your age";

  // age settings as well as interaction text
  private static final int MIN_AGE = 0;
  private static final int MAX_AGE = 140;
  private static final String ASK_FOR_NUMERICAL_STRING =
      "Please make sure to enter a numerical value\n"
          + "larger than "
          + MIN_AGE
          + " and smaller than "
          + MAX_AGE;
  private static final String ANNOUNCE_INVALID_AGE =
      "We are sorry to let you know that the age entered is Invalid";

  // gender interaction text
  private static final String ASK_FOR_GENDER =
      "Would you please enter your gender : MALE or FEMALE";

  // a message to announce successful registration
  private static final String REGISTRATION_COMPLETE = "Your account has been added successfully";

  // this variable stores the string that ends the INPUT when taking symptoms
  private static final String END_INPUT = "END";

  // symptoms interaction text
  private static final String ASK_FOR_SYMPTOMS =
      "Please enter all of your symptoms and enter " + END_INPUT + " at the end.";

  // disease interaction text
  private static final String DISEASE_ANNOUNCEMENT =
      "According to the provided symptoms and our data. " + "The most probable disease is ";
  private static final String DISEASE_INFO =
      "Some recommendations and information about your disease: ";
  private static final String NO_DISEASE_MATCH =
      "We are terribly sorry to let you know that none of the symptoms "
          + "passed match any of our diseases' symptoms.";
  private static final String No_DISEASE_INFO =
      "We are terribly sorry to let you know that we have little to "
          + "no information to provide concerning this disease. Our team will surely update the database soon!!";

  // doctor checking interaction text
  private static final String DOCTOR_CHECK =
      "Please seek professional treatment by the nearest doctor: ";
  private static final String DEFAULT_DOCTOR =
      "We are terribly sorry to let you know that your current city has "
          + "not been added to our database yet.";
  private static final String DEFAULT_DOCTOR_CHECK =
      "Please seek professional treatment by one of our best doctors: ";

  // these static variables are used to interact with PREMIUM clients
  // concerning INSURANCE SERVICES

  private static final String NO = "NO";

  private static final String INSURANCE_SERVICE =
      "Thank you for being one of our PREMIUM clients."
          + " Our application collaborates with the most trusted insurance companies:";

  private static final String PROMOTE_OFFERS =
      "Our application's users can benefit from significant offers"
          + " starting from "
          + InsuranceCompany.MIN_DISCOUNT_PERCENTAGE
          + " % up to "
          + InsuranceCompany.MAX_DISCOUNT_PERCENTAGE
          +" %";

  private static final String SELECT_COMPANY =
      "PLEASE ENTER YOUR INSURANCE COMPANY NAME OR "
          + "ENTER "
          + NO
          + " IF YOU COLLABORATE WITH ANOTHER AGENCY";

  private static final String INSURANCE_WARNING =
      "PLEASE MAKE SURE TO ENTER EITHER NO OR ONE OF THE " + " OUR PARTNERS NAMES";

  private static final String RECOMMEND_PARTNERS =
      "Unfortunately, your insurance company is not an official "
          + "partner yet. Please consider changing your insurance agency to take full advantage of your"
          + "PREMIUM membership";
  private static final String THANK_YOU =
      "Thank you for trusting our application. An agent from your insurance "
          + "company will contact you soon.";

  // ask the user to enter his membership
  private static final String ASK_FOR_MEMBERSHIP =
      "PLEASE SELECT ONE OF THE FOLLOWING MEMBERSHIPS " + "\nBASIC PRO PRO+ PREMIUM";

  // static Scanner used for INPUT:
  private static final Scanner INPUT = new Scanner(System.in);

  // this method makes sure that the address entered should be valid (according to the model
  // specified)
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
        if (realAge > MIN_AGE && realAge < MAX_AGE) break;
      } catch (NumberFormatException parseExp) {
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

    while (!(g.equalsIgnoreCase("MALE") || g.equalsIgnoreCase("FEMALE"))) {
      System.out.println(ASK_FOR_GENDER);
      gender = INPUT.nextLine();
      g = gender.trim().replaceAll("\\s", "");
    }
    return g.toUpperCase();
  }

  // validate Membership : the value should be one of the following :
  // BASIC, PRO, PRO+, PREMIUM
  public static String validateMembership() {
    System.out.println(ASK_FOR_MEMBERSHIP);
    String m = INPUT.nextLine().trim().replaceAll("\\s", "").toUpperCase();
    while ((Membership.getValue(m) == null)) {
      System.out.println(ASK_FOR_MEMBERSHIP);
      m = INPUT.nextLine().trim().replaceAll("\\s", "").toUpperCase();
    }
    return m;
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

    String Membership = validateMembership();

    System.out.println(REGISTRATION_COMPLETE);
    System.out.println();

    return new String[] {firstName, lastName, address, age, sex, Membership};
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

  // display the prediction's result
  public static Disease displayPrediction(Disease disease) {
    if (disease != null) {
      System.out.println(DISEASE_ANNOUNCEMENT + disease.getName());
    } else {
      System.out.println(NO_DISEASE_MATCH);
    }
    System.out.println();
    return disease;
  }

  // display the passed disease info
  public static String displayDiseaseInfo(Disease disease) {
    if (disease != null) {
      System.out.println(DISEASE_INFO);
      System.out.println(disease.getInfo());
      System.out.println();
      return disease.getInfo();
    }
    System.out.println(No_DISEASE_INFO);
    System.out.println();
    return No_DISEASE_INFO;
  }

  // display the nearest doctor according to the passed address
  // or announce that the patient's city is not on the list
  public static Doctor displayDoctor(Doctor doctor) {
    if (doctor.isMatchPatientCity()) {
      System.out.println(DOCTOR_CHECK);
    } else {
      System.out.println(DEFAULT_DOCTOR);
      System.out.println(DEFAULT_DOCTOR_CHECK);
    }
    System.out.println(doctor);
    System.out.println();
    return doctor;
  }

  // display the application's partners
  private static void displayCompanies(List<String> names) {
    for (String name : names) {
      System.out.print(name + " ");
    }
    System.out.println();
  }

  // this method will remove all trailing, heading and additional spaces and return an upperCase
  // version of the name
  private static String insuranceName(String s) {
    return s.replaceAll("\\s", "").trim().toUpperCase();
  }

  // this method will keep asking for user input until it receives either a partner's name
  // or a predetermined string indicating that the client has a different insurance company
  private static String validateCompanyName(List<String> names) {
    System.out.println(SELECT_COMPANY);
    String name = insuranceName(INPUT.nextLine());

    while (!(name.equals(NO) || names.contains(name))) {
      System.out.println(INSURANCE_WARNING);
      System.out.println(SELECT_COMPANY);
      name = insuranceName(INPUT.nextLine());
    }
    return name;
  }

  // this method will return the suitable output according to the company's name
  public static void reactToName(String companyName) {
    if (companyName.equals(NO)) {
      System.out.println(RECOMMEND_PARTNERS);
    } else {
      System.out.println(THANK_YOU);
    }
  }

  // this method wraps the user-application interaction concerning life insurance
  public static String insuranceProcedure(List<String> names) {
    // announce the service
    System.out.println(INSURANCE_SERVICE);
    // display which companies the app collaborates with
    displayCompanies(names);
    // promote offers
    System.out.println(PROMOTE_OFFERS);
    // ask and validate the company name

    return validateCompanyName(names);
  }

  // this method displays the insurance company's offer
  public static void announceOffer(String companyName, int discountPercentage) {
    System.out.println(
        "If the diagnosis is confirmed, "
            + companyName
            + " will cover "
            + discountPercentage
            + "% of the treatment cost");
  }
}
