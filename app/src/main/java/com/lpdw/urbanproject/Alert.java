package com.lpdw.urbanproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by OBYON on 16/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert {
    float threshold;
    int comparison;
    String description;
    Type type;
    String name;
    String slug;
}
