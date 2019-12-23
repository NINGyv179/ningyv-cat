package com.ningyv.smallcat.entity.vo;

/**
 * @author LCX
 * @create 2019-12-22 16:40
 */
public class MemberVo {
        private String loginacct;
    private String username;
    private String userpswd;
    private String phoneNum;
    private String randomCode;

    public MemberVo() {
        super();
    }

    public MemberVo(String loginacct, String username, String userpswd, String phoneNum, String randomCode) {
        this.loginacct = loginacct;
        this.username = username;
        this.userpswd = userpswd;
        this.phoneNum = phoneNum;
        this.randomCode = randomCode;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    @Override
    public String toString() {
        return "MemberVo{" +
                "loginacct='" + loginacct + '\'' +
                ", username='" + username + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", randomCode='" + randomCode + '\'' +
                '}';
    }
}
