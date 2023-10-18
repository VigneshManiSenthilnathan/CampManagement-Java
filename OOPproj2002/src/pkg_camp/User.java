package pkg_camp;

public interface User {
    String getUserID();
    String getPassword();
    void setPassword(String newPassword);
    String getFaculty();
}

public class UserClass implements User {
    private String userID;
    private String password;
    private String faculty;

    public UserClass(String userID, String faculty) {
        this.userID = userID;
        this.password = "password";
        this.faculty = faculty;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void getPassword() {
        return password;
    }

    @Override
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String getFaculty() {
        return faculty;
    }
}
