package ru.learn.pojo;

import java.util.Date;

public class Person {

    private Long id;
    private String familyName;
    private String givenName;
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
