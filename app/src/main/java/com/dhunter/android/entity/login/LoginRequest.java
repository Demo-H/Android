package com.dhunter.android.entity.login;

/**
 * Created by dhunter on 2018/7/25.
 */

public class LoginRequest {

    private String phone;
    private String pwd;
    private String code;
    private String newPwd;
    private String againPwd;
    private String inviteCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getAgainPwd() {
        return againPwd;
    }

    public void setAgainPwd(String againPwd) {
        this.againPwd = againPwd;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
