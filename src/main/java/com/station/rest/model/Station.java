package com.station.rest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Station {

    public Station() {
    }

    public Station(int stationId, String name, Boolean hdEnabled, String callSign) {
        this.stationId = stationId;
        this.name = name;
        this.hdEnabled = hdEnabled;
        this.callSign = callSign;
    }

    @ApiModelProperty(notes = "Application specific station ID")
    private int stationId;

    @ApiModelProperty(notes = "The station name")
    private String name;

    @ApiModelProperty(notes = "The hdEnabled station")
    private Boolean hdEnabled;

    @ApiModelProperty(notes = "Call sign station")
    private String  callSign;

}
