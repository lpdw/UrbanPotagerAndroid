package com.lpdw.urbanproject.Api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpdw.urbanproject.Garden;
import com.lpdw.urbanproject.Measure;

/**
 * Created by yassin on 03/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("user")
    public Me me;

    public Garden[] gardens;
    public Measure[] measures;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Me extends com.lpdw.urbanproject.Me{
    }
}