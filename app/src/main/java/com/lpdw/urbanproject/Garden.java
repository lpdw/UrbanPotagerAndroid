package com.lpdw.urbanproject;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by OBYON on 02/06/16.
 */
public class Garden {
    public Address address;
    public Coordinate coordinate;
    @JsonProperty("api_key")
    public String apiKey;
    public String description;
    @JsonProperty("is_public")
    public boolean isPublic;
    public Owner owner;
    @JsonProperty("show_location")
    public boolean showLocation;
    public String name;
    public String slug;

    public class Address{
        public String country;
        public String zipCode;
        public String city;
        public String line1;
        public String line2;
    }

    public class Coordinate{
        public float lng;
        public float lat;
    }

    public class Owner{
        public String username;
    }
}
