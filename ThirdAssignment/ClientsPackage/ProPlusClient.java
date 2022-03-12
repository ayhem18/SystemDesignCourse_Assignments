package clientspackage;

import visitorpackage.ClientVisitor;
import inputoutputpackage.Communication;
import doctorpackage.Doctor;

public class ProPlusClient extends ProClient {

  protected Doctor currentDoctor;
  protected String address;

  public ProPlusClient() {
    super();
    address = Communication.validateAddress();
  }

  public Doctor getCurrentDoctor() {
    return currentDoctor;
  }

  public void setCurrentDoctor(Doctor currentDoctor) {
    this.currentDoctor = currentDoctor;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String ADDRESS) {
    this.address = ADDRESS;
  }

  @Override
  public void accept(ClientVisitor clientVisitor) {
    clientVisitor.visitProPlusClient(this);
  }
}
