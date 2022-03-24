package proxy.protecttion_proxy;

import java.time.LocalDate;

public class PersonBeanImpl implements PersonBean {

  String name;
  String gender;
  LocalDate hiredDate;
  Grade grade;

  @Override
  public String toString() {
    return "PersonBeanImpl{" +
        "name='" + name + '\'' +
        ", gender='" + gender + '\'' +
        ", hiredDate=" + hiredDate +
        ", grade=" + grade +
        '}';
  }

  @Override
  public String readInfo(PersonBean person) {
   return person.toString();
  }

  @Override
  public Grade getGrade() {
   return this.grade;
  }

  public PersonBeanImpl(String name, String gender, LocalDate hiredDate, Grade grade) {
    this.name = name;
    this.gender = gender;
    this.hiredDate = hiredDate;
    this.grade = grade;
  }

  @Override
  public String getName() {
    return name;
  }
}
