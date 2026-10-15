package za.ac.cput.carpartmarket.DTO;

public class ResetPasswordDTO {
    private String email;
    private String newPassword;

    public String getEmail() { return email; }
    public String getNewPassword() { return newPassword; }

    public void setEmail(String email) { this.email = email; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}