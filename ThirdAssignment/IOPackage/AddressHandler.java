package inputoutputpackage;

/**
 * AddressHandler is a utility class that provides different functionalities to manipulate address
 * the address are assumed to be in the following form : City X Y the city can be considered a grid
 * with horizontal and vertical avenues When changing the model. All the changes will be reflected
 * only in this class.
 */
public class AddressHandler {
  // this value is used as convention to represent that two address do not belong to the same city
  // it is used when calculating the distance between two passed addresses
  public static final int NOT_SAME_CITY_CODE = Integer.MAX_VALUE;

  // remove all additional spaces including heading and trailing ones
  public static String legitimateAddress(String address) {
    return address.replaceAll("\\s{2,}", " ").trim();
  }

  // check whether the string passed contains only digits
  private static boolean isNumerical(String string) {
    for (char c : string.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  // check whether the string passed contains only alphabetic characters
  private static boolean isAlphabetic(String string) {
    for (char c : string.toCharArray()) {
      if (!Character.isAlphabetic(c) && c != '-') {
        return false;
      }
    }
    return true;
  }

  // check whether the passed address can be considered valid: according to our model
  public static boolean isValidAddress(String address) {
    String legitAd = legitimateAddress(address);
    String[] data = legitAd.split(" ");
    int l = data.length;

    if (l != 4 && l != 3) {
      return false;
    }
    for (int i = 0; i < l - 2; i++) {
      if (!(isAlphabetic(data[i]))) {
        return false;
      }
    }
    return (isNumerical(data[l - 1]) && isNumerical(data[l - 2]));
  }

  /**
   * This method extracts the string representing the city within the passes address
   *
   * @param address : either a patient's or a doctor address
   * @return Return a string representing the address's city
   */
  public static String getCityFromAddress(String address) {
    String legitAdd = legitimateAddress(address);
    String[] data = legitAdd.split(" ");

    if (data.length == 4) {
      return data[0] + " " + data[1];
    }
    return data[0];
  }

  /**
   * This method extracts the string representing the horizontal avenue number
   *
   * @param address : either a patient's or a doctor address
   * @return Return a string representing the X avenue number
   */
  public static int getXFromAddress(String address) {
    String legitAdd = legitimateAddress(address);
    String[] data = legitAdd.split(" ");

    if (data.length == 4) {
      return Integer.parseInt(data[2]);
    }
    return Integer.parseInt(data[1]);
  }

  /**
   * This method extracts the string representing the vertical avenue number
   *
   * @param address : either a patient's or a doctor address
   * @return Return a string representing the Y avenue number
   */
  public static int getYFromAddress(String address) {
    String legitAdd = legitimateAddress(address);
    String[] data = legitAdd.split(" ");

    if (data.length == 4) {
      return Integer.parseInt(data[3]);
    }
    return Integer.parseInt(data[2]);
  }

  /**
   * This method calculates the distance between two address according to our model
   *
   * @param address1 the first address
   * @param address2 the second address
   * @return Return an integer representing the distance between the two addresses
   */
  public static int distanceBetweenTwoAddresses(String address1, String address2) {
    String city1 = getCityFromAddress(address1);
    String city2 = getCityFromAddress(address2);

    if (city1.equalsIgnoreCase(city2)) {
      int x1 = getXFromAddress(address1);
      int y1 = getYFromAddress(address1);

      int x2 = getXFromAddress(address2);
      int y2 = getYFromAddress(address2);

      return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
    }
    return NOT_SAME_CITY_CODE;
  }
}
