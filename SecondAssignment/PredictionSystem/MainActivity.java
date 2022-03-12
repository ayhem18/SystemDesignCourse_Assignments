package sosad;

import java.util.ArrayList;

import java.util.List;

// THE TEAM : ADEL KRYLOVA - VLADIMIR ZELENOKOR - MADE OKA RESIA WEDAMERTA - AYHEM BOUABID
// THE DATE : 04/11/2021
// THIS IS OUR SOLUTION OF THE SECOND SSAD HOMEWORK PROJECT. OUR TASK IS NUMBER 21.

public class MainActivity {
  private static final List<Client> CLIENTS = new ArrayList<>();
  private static final int PATIENTS_NUMBER = 2;

  public static void activityOnCreate() {
    CLIENTS.add(new Client());
  }

  public static void activityOnVisit(Client client) {
    client.askForService();
  }

  public static void main(String[] args) {
    for (int i = 0; i < PATIENTS_NUMBER; i++) {
      activityOnCreate();
      activityOnVisit(CLIENTS.get(i));
      System.out.println();
    }
  }
}
