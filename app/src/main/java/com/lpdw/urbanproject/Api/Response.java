package com.lpdw.urbanproject.Api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpdw.urbanproject.Me;

/**
 * Created by yassin on 03/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("user")
    public Me me;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Me extends com.lpdw.urbanproject.Me{
    }
}