package com.omercangoktas.flight_search_api.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omercangoktas.flight_search_api.exception.NotFoundException;
import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.service.FlightService;

import ch.qos.logback.classic.Logger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/flights")
@Api(value = "Flight Management System", description = "Operations pertaining to flight management")
public class FlightController {
    Logger logger = (Logger) LoggerFactory.getLogger(FlightController.class);
    private FlightService flightService;

    public FlightController(FlightService flightService ) {
        this.flightService = flightService;
    }

    @ApiOperation(value = "View a list of available flights", response = List.class)
    @GetMapping()
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @ApiOperation(value = "Get a flight by ID")
    @GetMapping(value = "/{flightId}")
    public Flight getFlightById(
            @ApiParam(value = "ID of the flight to retrieve", required = true) @PathVariable Long flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }

    @ApiOperation(value = "Add a new flight")
    @PostMapping
    public Flight createFlight(
            @ApiParam(value = "Flight object to be created", required = true) @RequestBody Flight flight) {
        logger.info("Creating new flight: " + flight);
        return flightService.createFlight(flight);
    }

    @ApiOperation(value = "Update an existing flight")
    @PutMapping(value = "/{flightId}")
    public Flight updateFlight(
            @ApiParam(value = "ID of the flight to update", required = true) @PathVariable Long flightId,
            @ApiParam(value = "Updated flight object", required = true) @RequestBody Flight flight) throws Exception {
        return flightService.updateFlight(flightId, flight);
    }

    @ApiOperation(value = "Delete a flight")
    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(
            @ApiParam(value = "ID of the flight to delete", required = true) @PathVariable Long flightId) {
        try {
            flightService.deleteFlight(flightId);
            return ResponseEntity.ok("Flight with id " + flightId + " deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete flight with id " + flightId);
        }
    }
}





// package com.omercangoktas.flight_search_api.controller;

// import java.util.List;

// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.omercangoktas.flight_search_api.exception.NotFoundException;
// import com.omercangoktas.flight_search_api.model.Flight;
// import com.omercangoktas.flight_search_api.service.AirportService;
// import com.omercangoktas.flight_search_api.service.FlightService;

// import ch.qos.logback.classic.Logger;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @RestController
// @RequestMapping(value = "/flights")
// @Api(value = "Flight Management System", description = "Operations pertaining to flight management")
// public class FlightController {
//     Logger logger = (Logger) LoggerFactory.getLogger(FlightController.class);
//     private FlightService flightService;

//     @Autowired
//     public FlightController(FlightService flightService ) {
//         this.flightService = flightService;
//     }

//     // get all airports using service -> repository
//     @ApiOperation(value = "View a list of available flights", response = List.class)
//     @GetMapping()
//     public List<Flight> getAllFlights() {
//         return flightService.getAllFlights();
//     }

//     @ApiOperation(value = "Get a flight by ID")
//     @GetMapping(value = "/{airportId}")
//     public Flight getFlightById(@PathVariable Long airportId) throws Exception {
//         return flightService.getFlightById(airportId);
//     }

//     // creating new flight
//     @PostMapping
//     public Flight createFlight(@RequestBody Flight flight) {
//         logger.info("Creating new flight: " + flight);
//         return flightService.createFlight(flight);
//     }

//     @PutMapping(value = "/{flightId}")
//     public Flight updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) throws Exception {
//         return flightService.updateFlight(flightId, flight);
//     }

//     @DeleteMapping("/{flightId}")
//     public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
//         try {
//             flightService.deleteFlight(flightId);
//             return ResponseEntity.ok("Flight with id " + flightId + " deleted successfully.");
//         } catch (NotFoundException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete flight with id " + flightId);
//         }
//     }
// }
