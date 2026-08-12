package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    protected Long userid;

    @Embedded
    protected Name name;

    protected String email;
    protected String password;
    protected String phoneNumber;
    protected String createdAt;

    protected User() {
    }


    public Long getUserid() {
        return userid;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }


    }
