package com.cskaoyan.mall.bean;

public class ChangePwdAdmin {
    private String email;
    private String oldPwd;
    private String newPwd;
    private String confirmedPwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmedPwd() {
        return confirmedPwd;
    }

    public void setConfirmedPwd(String confirmedPwd) {
        this.confirmedPwd = confirmedPwd;
    }

    @Override
    public String toString() {
        return "ChangePwdAdmin{" +
                "email='" + email + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", confirmedPwd='" + confirmedPwd + '\'' +
                '}';
    }
}
