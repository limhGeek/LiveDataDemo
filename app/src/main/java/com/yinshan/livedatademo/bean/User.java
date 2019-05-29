package com.yinshan.livedatademo.bean;

/**
 * @author limh
 * @function
 * @date 2019/2/14 14:13
 */
public class User {
    private String userName;
    private String password;
    private String phone;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{\"User\":{"
                + "\"userName\":\""
                + userName + '\"'
                + ",\"password\":\""
                + password + '\"'
                + ",\"phone\":\""
                + phone + '\"'
                + "}}";

    }
}
