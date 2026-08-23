package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Register {

    @Id
    private String registrationId;

    @ManyToOne
    @JoinColumn(name = "user_userid")
    private User user;
    private LocalDate registrationDate;
    private String status;

    public void setUser(User user) {
        this.user = user;
    }

    public Register() {
    }

    public Register (Builder builder){
        this.registrationId = builder.registrationId;
        this.user = builder.user;
        this.registrationDate = builder.registrationDate;
        this.status = builder.status;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Register{" +
                "registrationId='" + registrationId + '\'' +
                ", user=" + user +
                ", registrationDate=" + registrationDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder{
        private String registrationId;
        private User user;
        private LocalDate registrationDate;
        private String status;

        public Builder setRegistrationId(String registrationId) {
            this.registrationId = registrationId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Register register){
            this.registrationId = register.registrationId;
            this.user = register.user;
            this.registrationDate = register.registrationDate;
            this.status = register.status;
            return this;
        }

        public Register build(){
            return new Register(this);
        }
    }
}
