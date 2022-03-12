package sosadmemberships;

import sosad.Client;
import sosad.Communication;
import sosad.Doctor;
import sosad.DoctorFinder;

/**
 * The ProPlusServiceProvider is a concrete decorator created for PRO+ clients In addition to
 * disease prediction ensured by super.provideService(), this class finds the nearest doctor
 * according to the client's address. The functionality is delegated to the protected doctorFinder
 * field
 */
public class ProPlusServiceProvider extends ProServiceProvider {
  protected DoctorFinder doctorFinder;
  protected Doctor ourDoctor;

  public ProPlusServiceProvider() {
    super();
    this.doctorFinder = DoctorFinder.getInstance();
  }

  @Override
  public void provideService(Client client) {
    // calling the default service: disease prediction
    super.provideService(client);
    // finding and displaying the closest doctor
    ourDoctor = doctorFinder.findDoctor(client.getAddress());
    Communication.displayDoctor(ourDoctor);
  }
}
