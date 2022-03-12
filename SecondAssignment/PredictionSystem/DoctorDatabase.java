package sosad;

import java.util.*;

public class DoctorDatabase {
  private static final List<Doctor> DOCTORS = new ArrayList<>();
  private static List<String> CITIES = new ArrayList<>();
  private static int NUMBER_OF_CITIES = 10;
  private static int NUMBER_OF_DOCTORS = 20;
  private static final int MAX_X = 100;
  private static final int MAX_Y = 100;

  /**
   * private static database and private constructor and a single public entry point : Singleton
   * pattern
   */
  private static volatile DoctorDatabase database;

  // this method is used in the constructor to populate "doctors" with a set of default objects
  private static String addressGenerator() {
    Random generator = new Random();
    int x = generator.nextInt(MAX_X);
    int y = generator.nextInt(MAX_Y);
    int cityIndex = generator.nextInt(NUMBER_OF_CITIES);
    return CITIES.get(cityIndex) + " " + x + " " + y;
  }

  /*
   * a constructor where a set of default cities are added to the CITIES field
   * and a set of default set of doctors to the doctors field
   */
  private DoctorDatabase() {
    CITIES =
        Arrays.asList(
            "MOSCOW",
            "SAINT PETERSBURG",
            "NOVOSIBIRSK",
            "YEKATERINBURG",
            "KAZAN",
            "NIZHNY NOVGOROD",
            "CHELYABINSK",
            "SAMARA",
            "OMSK",
            "ROSTOV-ON-DON");
    for (int i = 0; i < NUMBER_OF_DOCTORS; i++) {
      DOCTORS.add(new Doctor("doctor " + (i + 1), addressGenerator()));
    }
  }

  public static DoctorDatabase getInstance() {
    if (database == null) {
      // to make the call thread-safe
      synchronized (DoctorDatabase.class) {
        if (database == null) {
          database = new DoctorDatabase();
        }
      }
    }
    return database;
  }

  // this method is called if a newly- added doctor resides in a new city
  private void addCityIfNecessary(String address) {
    String city = AddressHandler.getCityFromAddress(address);
    if (!CITIES.contains(city)) {
      CITIES.add(city);
      NUMBER_OF_CITIES++;
    }
  }

  // add doctor passing name and address
  public void addDoctor(String name, String address) {
    DOCTORS.add(new Doctor(name, address));
    NUMBER_OF_DOCTORS++;
    addCityIfNecessary(address);
  }

  public void addDoctor(Doctor doctor) {
    DOCTORS.add(doctor);
    NUMBER_OF_DOCTORS++;
    addCityIfNecessary(doctor.getAddress());
  }

  // getter for the doctors field
  public List<Doctor> getDoctors() {
    return DOCTORS;
  }
}
