package com.station.rest.controller;

import com.station.rest.model.Station;
import com.station.rest.model.StationDataRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="StationData", description="Operations pertaining to Stations", produces = "application/json")
@RequestMapping("/station")
public class StationDataController {

    @Autowired
    StationDataRepository mapper;

   public StationDataController(){ }

    @ApiOperation(value = "Add a station")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveStation(@RequestBody Station station){
        mapper.insert(station);
        return new ResponseEntity("Station saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Remove a station")
    @RequestMapping(value="/delete/{stationId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer stationId){
        mapper.deleteById(stationId);
        return new ResponseEntity("Station removed successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a station")
    @RequestMapping(value = "/update/{stationId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateStation(@PathVariable Integer stationId, @RequestBody Station station){
        Station stationData = mapper.findByStationId(stationId);
        stationData.setName(station.getName());
        stationData.setHdEnabled(station.getHdEnabled());
        stationData.setCallSign(station.getCallSign());
        mapper.update(stationData);
        return new ResponseEntity("Station updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Search a station with hdEnabled",response = Station.class)
    @RequestMapping(value = "/searchwithhdenabled/{hdEnabled}", method= RequestMethod.GET, produces = "application/json")
    public List<Station>  showStationDataWithHdEnabled(@PathVariable Boolean hdEnabled){
        List<Station> station = mapper.findByHdEnabledStations(hdEnabled);
        return station;
    }

    @ApiOperation(value = "Search a station with an stationId",response = Station.class)
    @RequestMapping(value = "/searchwithstationid/{stationId}", method= RequestMethod.GET, produces = "application/json")
    public Station showStationWithStationId(@PathVariable Integer stationId){
        Station station = mapper.findByStationId(stationId);
        return station;
    }

    @ApiOperation(value = "Search a station with an Name",response = Station.class)
    @RequestMapping(value = "/searchwithname/{name}", method= RequestMethod.GET, produces = "application/json")
    public List<Station>  showStationWithStationName(@PathVariable String name){
        List<Station> station = mapper.findByStationName(name);
        return station;
    }

    @ApiOperation(value = "View a list of available stations",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public List<Station> list(){
        List<Station> stationsList = mapper.findAll();
        return stationsList;
    }
}

