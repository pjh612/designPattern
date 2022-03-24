package proxy.protecttion_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PersonInvocationHandler implements InvocationHandler {

  PersonBean person;

  public PersonInvocationHandler(PersonBean person) {
    this.person = person;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      if (method.getName().equals("readInfo")) {
        if (((PersonBean) proxy).getGrade() == Grade.Junior && ((PersonBean) args[0]).getGrade() == Grade.Senior) {
          System.out.println("접근 권한이 없습니다.");
          throw new IllegalAccessException();
        } else {
          return method.invoke(person, args);
        }
      } else if (method.getName().startsWith("get")) {
        return method.invoke(person, args);
      }
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
    return null;
  }
}
