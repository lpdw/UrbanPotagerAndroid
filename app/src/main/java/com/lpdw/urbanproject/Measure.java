package com.lpdw.urbanproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by OBYON on 16/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure{
    public float value;
    public String createdAt;
}