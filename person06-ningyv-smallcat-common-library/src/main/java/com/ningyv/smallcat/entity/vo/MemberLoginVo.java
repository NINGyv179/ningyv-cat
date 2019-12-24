package com.ningyv.smallcat.entity.vo;

/**
 * @author LCX
 * @create 2019-12-23 21:50
 */
public class MemberLoginVo {
    private String username;
    private String userpswd;

    public MemberLoginVo() {
        super();
    }

    public MemberLoginVo(String username, String userpswd) {
        this.username = username;
        this.userpswd = userpswd;
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

    @Override
    public String toString() {
        return "MemberLoginVo{" +
                "username='" + username + '\'' +
                ", userpswd='" + userpswd + '\'' +
                '}';
    }
}
