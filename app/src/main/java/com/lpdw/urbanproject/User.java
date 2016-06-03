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
    public Text token;
    @JsonProperty("refresh_token")
    public Text refreshToken;
    public Boolean enabled;
    public ArrayList<String> roles;

    public User(String username, String email, String plainPassword) {
        this.username = username;
        this.email = email;
        this.plainPassword = plainPassword;
    }
    public User() {

    }
}
