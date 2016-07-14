package lab.model.entity;

import lab.model.enums.Sex;
import lab.utils.DateFormater;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private Sex sex;
    private Date birthDay;

    public Person(String name, String surname, Sex sex, Date birthDay) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public Person(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!name.equals(person.name)) return false;
        if (!surname.equals(person.surname)) return false;
        if (sex != person.sex) return false;
        return birthDay.equals(person.birthDay);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + birthDay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "name: '" + name + '\'' +
                " | surname: '" + surname + '\'' +
                " | sex: " + sex.getStringValue() +
                " | birthday: " + DateFormater.simpleDateFormat(birthDay);
    }
}
