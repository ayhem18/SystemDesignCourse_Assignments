import clientspackage.BasicClient;
import inputoutputpackage.Communication;
import visitorpackage.ClientVisitor;
import visitorpackage.ServiceVisitor;
import java.util.ArrayList;
import java.util.List;

// THE TEAM : ADEL KRYLOVA - VLADIMIR ZELENOKOR - MADE OKA RESIA WEDAMERTA - AYHEM BOUABID
// THE DATE : 04/11/2021
// THIS IS OUR SOLUTION OF THE SECOND SSAD HOMEWORK PROJECT. OUR TASK IS NUMBER 21.

public class MainActivity {
  private static final List<BasicClient> CLIENTS = new ArrayList<>();
  private static final int PATIENTS_NUMBER = 2;

  public static void activityOnCreate() {
    CLIENTS.add(Communication.registerClient());
  }

  public static void activityOnVisit(BasicClient client, ClientVisitor visitor) {
    client.accept(visitor);
  }

  public static void main(String[] args) {
    ClientVisitor ourVisitor = new ServiceVisitor();
    for (int i = 0; i < PATIENTS_NUMBER; i++) {
      activityOnCreate();
      activityOnVisit(CLIENTS.get(i), ourVisitor);
      System.out.println();
    }
  }
}
