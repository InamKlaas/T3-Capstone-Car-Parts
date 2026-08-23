package za.ac.cput.carpartmarket.Domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;


//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "userType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Buyer.class, name = "buyer"),
        @JsonSubTypes.Type(value = Seller.class, name = "seller"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})
public abstract class User {

    @Id
    protected String userid;

    @Embedded
    protected Name name;

    protected String email;
    protected String password;
    protected String phoneNumber;
    protected String createdAt;

    protected User() {
    }


    public String getUserid() {
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
