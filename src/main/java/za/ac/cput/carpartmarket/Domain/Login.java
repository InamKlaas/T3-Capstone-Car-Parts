package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "login")
public class Login {
    @Id
    private String loginId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    private User user;
    private String email;
    private String password;
    private LocalDateTime loginDate;
    private String status;

    protected Login() {
    }

    public Login(Builder builder) {
        this.loginId = builder.loginId;
        this.user = builder.user;
        this.email = builder.email;
        this.password = builder.password;
        this.loginDate = builder.loginDate;
        this.status = builder.status;
    }

    public boolean authenticate() {
        return email != null && password != null;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public String getStatus() {
        return status;
    }

    public String getLoginId() {
        return loginId;
    }

    public User getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginId=" + loginId +
                ", user=" + user +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loginDate=" + loginDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {

        private String loginId;
        private User user;
        private String email;
        private String password;
        private LocalDateTime loginDate;
        private String status;

        public Builder setLoginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLoginDate(LocalDateTime loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Login login) {
            this.loginId = login.loginId;
            this.user = login.user;
            this.email = login.email;
            this.password = login.password;
            this.loginDate = login.loginDate;
            this.status = login.status;
            return this;
        }

        public Login build() {
            return new Login(this);
        }
    }
}
