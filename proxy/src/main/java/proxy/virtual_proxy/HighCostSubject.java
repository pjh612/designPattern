package proxy.virtual_proxy;

import proxy.virtual_proxy.secret.SecretProvider;

public class HighCostSubject implements Subject {

  private String plainData;

  public HighCostSubject(String secretData) throws Exception {
    this.plainData = SecretProvider.decryptAes(secretData, "oingisprettyintheworld1234567890");
  }

  public String fetch() {
    return plainData;
  }
}
