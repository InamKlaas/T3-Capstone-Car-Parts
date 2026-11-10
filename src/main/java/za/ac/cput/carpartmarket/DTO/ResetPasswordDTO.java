package za.ac.cput.carpartmarket.DTO;

public class ResetPasswordDTO {
    private String email;
    private String newPassword;
    private String code;

    public String getEmail() { return email; }
    public String getNewPassword() { return newPassword; }

    public String getCode() {
        return code;
    }

    public void setEmail(String email) { this.email = email; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    public void setCode(String code){
        this.code = code;
    }
}