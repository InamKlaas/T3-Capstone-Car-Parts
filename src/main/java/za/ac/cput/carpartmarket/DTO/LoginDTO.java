package za.ac.cput.carpartmarket.DTO;



import java.time.LocalDateTime;

public class LoginDTO {
    private String loginId;
    private String userid;
    private String email;
    private String password;
    private LocalDateTime loginDate;
    private String status;

    public LoginDTO() {}

    public LoginDTO(String loginId, String userid, String email, String password,
                    LocalDateTime loginDate, String status) {
        this.loginId = loginId;
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.loginDate = loginDate;
        this.status = status;
    }

    public String getLoginId() { return loginId; }
    public String getUserid() { return userid; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public LocalDateTime getLoginDate() { return loginDate; }
    public String getStatus() { return status; }
}