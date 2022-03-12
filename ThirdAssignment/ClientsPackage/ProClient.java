package clientspackage;

import visitorpackage.ClientVisitor;
import java.util.ArrayList;
import java.util.List;

public class ProClient extends BasicClient {
  protected List<String> listOfRecommendations;

  public ProClient() {
    super();
    listOfRecommendations = new ArrayList<>();
  }

  public List<String> getRecommendations() {
    return listOfRecommendations;
  }

  public void addRecommendation(String recommendation) {
    this.listOfRecommendations.add(recommendation);
  }

  @Override
  public void accept(ClientVisitor clientVisitor) {
    clientVisitor.visitProClient(this);
  }
}
