package za.ac.cput.carpartmarket.DTO;

import java.time.LocalDate;

public class RegisterDTO {
    private String registrationId;
    private String userid;
    private LocalDate registrationDate;
    private String status;

    public RegisterDTO() {}

    public RegisterDTO(String registrationId, String userid, LocalDate registrationDate, String status) {
        this.registrationId = registrationId;
        this.userid = userid;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public String getRegistrationId() {
        return registrationId; }
    public String getUserid() {
        return userid; }
    public LocalDate getRegistrationDate() {
        return registrationDate; }
    public String getStatus() {
        return status; }
}
