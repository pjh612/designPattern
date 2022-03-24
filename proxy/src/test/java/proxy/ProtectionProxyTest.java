package proxy;

import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proxy.protecttion_proxy.Grade;
import proxy.protecttion_proxy.PersonBean;
import proxy.protecttion_proxy.PersonBeanImpl;
import proxy.protecttion_proxy.PersonInvocationHandler;

public class ProtectionProxyTest {

  HashMap<String, PersonBean> db = new HashMap<>();

  @BeforeEach
  public void before() {
    initDummy();
  }

  void initDummy() {
    PersonBean park = new PersonBeanImpl("jinhyung Park", "man", LocalDate.now(), Grade.Senior);
    PersonBean lee = new PersonBeanImpl("joongddak Lee","man",LocalDate.now(), Grade.Junior);
    PersonBean kim = new PersonBeanImpl("soohan Kim","man",LocalDate.now(), Grade.Junior);
    db.put(park.getName(), park);
    db.put(lee.getName(), lee);
    db.put(kim.getName(), kim);
  }

  PersonBean getProxy(PersonBean person) {
    return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new PersonInvocationHandler(person));
  }

  PersonBean getPersonFromDb(String name) {
    return db.get(name);
  }

  @Test
  public void test() {
    PersonBean park = getProxy(getPersonFromDb("jinhyung Park"));
    PersonBean lee = getProxy(getPersonFromDb("joongddak Lee"));
    PersonBean kim = getProxy(getPersonFromDb("soohan Kim"));

    //시니어가 주니어의 인사 정보를 열람한다.
    Assertions.assertEquals(lee.toString(), park.readInfo(lee));
    Assertions.assertEquals(kim.toString(), park.readInfo(kim));

    //주니어가 시니어 인사 정보를 열람한다.
    try {
      lee.readInfo(park);
    } catch (Exception e) {
      Assertions.assertEquals(IllegalAccessException.class, e.getCause().getClass());
    }
    try {
      kim.readInfo(park);
    } catch (Exception e) {
      Assertions.assertEquals(IllegalAccessException.class, e.getCause().getClass());
    }

    //주니어가 주니어 인사 정보를 열람한다.
    Assertions.assertEquals(kim.toString(),lee.readInfo(kim));

  }
}
