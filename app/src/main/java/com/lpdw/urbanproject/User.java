package com.lpdw.urbanproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by OBYON on 30/05/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String username;
    public String email;
    public String plainPassword;
    public String token;
    public String refreshToken;
    public Boolean enabled;
    public ArrayList<String> roles;

    public User(String username, String plainPassword, String email) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.email = email;
    }

    public User(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
    }

    public User() {

    }
}
