package proxy.virtual_proxy;

public class ProxySubject implements Subject{

  private final String secureData;
  HighCostSubject subject;

  public ProxySubject(String secureData) {
    this.secureData = secureData;
  }

  @Override
  public String fetch() throws Exception {
    if (subject == null)
    {
      subject = new HighCostSubject(secureData);
    }
    return "proxy :" + subject.fetch();
  }
}
