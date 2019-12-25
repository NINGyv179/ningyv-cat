package com.ningyv.smallcat.entity.vo;

/**
 * @author LCX
 * @create 2019-12-23 21:22
 */
public class MemberSuccessVo {


    private String loginacct;
    private String username;
    private String token;

    public MemberSuccessVo() {
        super();
    }

    public MemberSuccessVo(String loginacct, String username, String token) {
        this.loginacct = loginacct;
        this.username = username;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MemberSuccessVo{" +
                "loginacct='" + loginacct + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
