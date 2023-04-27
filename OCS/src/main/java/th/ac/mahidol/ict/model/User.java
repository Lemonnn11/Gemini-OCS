package th.ac.mahidol.ict.model;


import lombok.*;

public class User {

    private int id;
    private String fname;
    private String lname;
    private String email;
    private String accessLevel;

    public User(){

    }

    public User(int id, String fname, String lname, String username, String password, String accessLevel){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
