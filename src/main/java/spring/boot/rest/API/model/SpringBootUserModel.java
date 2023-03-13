package spring.boot.rest.API.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "springbootuser")
public class SpringBootUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;
    /*
     * @Column on each Java instance variable allows defining a set of properties like name, length etc.
     * The name property will be the name of the field in the database table that this instance variable will map to.
     * */
    @Column(name = "user_full_name")
    private String userFullName;
    @Column(name = "user_father_name")
    private String userFatherName;
    @Column(name = "user_Email")
    private String userEmail;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "active_user")
    private Boolean active_user;

    public SpringBootUserModel() {
    }

    public SpringBootUserModel(int userID, String userFullName, String userFatherName, String userEmail,
                               String gender, int age, String address, Boolean active_user) {
        this.userID = userID;
        this.userFullName = userFullName;
        this.userFatherName = userFatherName;
        this.userEmail = userEmail;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.active_user = active_user;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFatherName() {
        return userFatherName;
    }

    public void setUserFatherName(String userFatherName) {
        this.userFatherName = userFatherName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isUserActive() {
        return active_user;
    }

    public void setActive_user(Boolean active_user) {
        this.active_user = active_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringBootUserModel that = (SpringBootUserModel) o;
        return userID == that.userID && age == that.age && Objects.equals(userFullName, that.userFullName) && Objects.equals(userFatherName, that.userFatherName) && Objects.equals(userEmail, that.userEmail) && Objects.equals(gender, that.gender) && Objects.equals(address, that.address) && Objects.equals(active_user, that.active_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userFullName, userFatherName, userEmail, gender, age, address, active_user);
    }
}
