package com.example.conflab;

public class Users {
    String profilePic, email, uName, password, uid, lastMsg, status;

    // Default constructor
    public Users() {
        // Default constructor
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getEmail() {
        return email;
    }

    public String getUName() {
        return uName;
    }

    public String getPassword() {
        return password;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public String getStatus() {
        return status;
    }

    public Users(String uid, String uName, String email, String password, String profilePic, String status) {
        this.uid = uid;
        this.uName = uName;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.status = status;
    }
}
