package com.naadworks.lumos.entry;

import java.io.Serializable;

public class UserEntry implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String country;

    private String email;

    public UserEntry() {
    }

    public UserEntry(Long id) {
        this.id = id;
    }

    public UserEntry(Long id, String name, Integer age, String country, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
