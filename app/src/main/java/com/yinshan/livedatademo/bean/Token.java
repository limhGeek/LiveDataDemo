package com.yinshan.livedatademo.bean;

/**
 * @author limh
 * @function
 * @date 2019/5/29 15:09
 */
public class Token {
    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @Override
    public String toString() {
        return "{\"Token\":{"
                + "\"Token\":\""
                + Token + '\"'
                + "}}";

    }
}
