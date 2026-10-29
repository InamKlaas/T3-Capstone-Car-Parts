package za.ac.cput.carpartmarket.DTO;

public class UserResponseDTO {
    private String userid;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String token;

    public UserResponseDTO(String userid, String firstName, String lastName, String email, String role,String token) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
    public String getToken() {return token;}

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {this.token = token;}

}
