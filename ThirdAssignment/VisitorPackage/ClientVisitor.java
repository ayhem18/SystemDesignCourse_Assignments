package visitorpackage;

import clientspackage.BasicClient;
import clientspackage.PremiumClient;
import clientspackage.ProClient;
import clientspackage.ProPlusClient;

public interface ClientVisitor {
  void visitBasicClient(BasicClient client);

  void visitProClient(ProClient client);

  void visitProPlusClient(ProPlusClient client);

  void visitPremiumClient(PremiumClient client);
}
