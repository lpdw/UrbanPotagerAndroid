package com.lpdw.urbanproject.Api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpdw.urbanproject.Alert;
import com.lpdw.urbanproject.GardenConfigurations;
import com.lpdw.urbanproject.Measure;
import com.lpdw.urbanproject.Type;

/**
 * Created by OBYON on 16/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse {
    public Measure[] measures;
    public Alert[] alerts;
    public Type[] types;
    public GardenConfigurations[] gardenConfigurationses;
    @JsonProperty("total_items")
    public String totalItems;
    @JsonProperty("item_per_page")
    public int itemPePage;
    public int page;
}