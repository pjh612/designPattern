package proxy.virtual_proxy;

import java.util.ArrayList;
import java.util.List;
import proxy.virtual_proxy.secret.SecretProvider;

public class VirtualProxyTest {

  public static void main(String[] args) throws Exception {
    List<Subject> normalList = new ArrayList<>();
    List<Subject> proxyList = new ArrayList<>();

    System.out.println("=== 일반 객체로 추가 ===");
    long sTime =System.currentTimeMillis();
    normalList.add(new HighCostSubject(SecretProvider.encryptAes("park","oingisprettyintheworld1234567890")));
    normalList.add(new HighCostSubject(SecretProvider.encryptAes("lee","oingisprettyintheworld1234567890")));
    System.out.println("객체 생성까지 "+ (System.currentTimeMillis() - sTime)+"millis 소요");
    System.out.println("=== fetch 사용 시점 ===");
    for (Subject subject : normalList) {
      subject.fetch();
    }

    System.out.println("=== 프록시 객체로 추가 ===");
    sTime = System.currentTimeMillis();
    proxyList.add(new ProxySubject(SecretProvider.encryptAes("park","oingisprettyintheworld1234567890")));
    proxyList.add(new ProxySubject(SecretProvider.encryptAes("lee","oingisprettyintheworld1234567890")));
    System.out.println("객체 생성까지 "+ (System.currentTimeMillis() - sTime)+"millis 소요");
    System.out.println("=== proxy 통해 fetch 사용 시점 ===");
    for (Subject subject : proxyList) {
      subject.fetch();
    }
  }
}
