package clientspackage;

public enum Gender {
  MALE,
  FEMALE;

  /**
   * this method assures passing sex as a string and getting the corresponding enum value it filers
   * additional spaces and ignores casing
   *
   * @param string: the passed string as sex
   * @return Returns the corresponding Gender value, the default is set to Gender.MALE
   */
  public static Gender getGender(String string) {
    // this method returns the corresponding gender regardless of the extra spaces and the casing
    // the default gender will be Male in case of misspelling
    if (string.trim().replaceAll("\\s", "").equalsIgnoreCase("FEMALE")) {
      return Gender.FEMALE;
    }
    return Gender.MALE;
  }
}
