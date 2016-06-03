package com.lpdw.urbanproject.Api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lpdw.urbanproject.User;

import java.util.ArrayList;

/**
 * Created by yassin on 03/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    public User user;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User extends com.lpdw.urbanproject.User{

        public User(String username, String email, String plainPassword) {
            super(username, email, plainPassword);
        }

        public User(){
            super();
        }
    }
}