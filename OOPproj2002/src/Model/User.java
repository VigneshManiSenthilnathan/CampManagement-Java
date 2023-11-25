package model;

public interface User {
    String getUserID();

    String getPassword();

    void setPassword(String newPassword);

    String getFaculty();

    String getUserType();
}