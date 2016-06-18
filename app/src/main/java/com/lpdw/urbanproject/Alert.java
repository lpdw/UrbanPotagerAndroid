package com.lpdw.urbanproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by OBYON on 16/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert {
    public float threshold;
    public int comparison;
    public String description;
    public Type type;
    public String name;
    public String slug;
}
