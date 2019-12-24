package com.ningyv.smallcat.entity.vo;

/**
 * @author LCX
 * @create 2019-12-23 21:22
 */
public class MemberSuccessVo {

    private String username;
    private String token;

    public MemberSuccessVo() {
        super();
    }

    public MemberSuccessVo(String username, String token) {
        this.username = username;
        this.token = token;
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
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
