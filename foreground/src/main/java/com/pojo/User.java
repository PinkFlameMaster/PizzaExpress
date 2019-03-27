package com.pojo;

public class User {
    private String phoneNum;
    private String password;
    private String nickname;
    private String city;
    private String birthday;
    private boolean deleted;

    public User(){

    }

    public User(String phoneNum, String password, String nickname, String city, String birthday, boolean deleted) {
        this.phoneNum = phoneNum;
        this.password = password;
        this.nickname = nickname;
        this.city = city;
        this.birthday = birthday;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
