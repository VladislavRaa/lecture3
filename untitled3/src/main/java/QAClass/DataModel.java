package QAClass;

import java.util.Calendar;

public class DataModel {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String sex;
    private Calendar dob;
    private Long itn;
    private int postcode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int house;
    private int apartment;

    public DataModel() {
    }

    public DataModel(String name, String surname, String patronymic, int age,
                     String sex, Calendar dob, Long itn, int postcode,
                     String country, String region, String city, String street,
                     int house, int apartment) {

        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.sex = sex;
        this.dob = dob;
        this.itn = itn;
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
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

    public String getSatronymic() {
        return patronymic;
    }

    public void setSatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Calendar getDOB() {
        return dob;
    }

    public void setDOB(Calendar dob) {
        this.dob = dob;
    }

    public Long getTIN() {
        return itn;
    }

    public void setTIN(Long itn) {
        this.itn = itn;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }
}