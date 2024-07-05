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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/flights")
@Tag(name = "Flight API", description = "API for managing flights")
public class FlightController {
    Logger logger = (Logger) LoggerFactory.getLogger(FlightController.class);
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "Get all flights", description = "Retrieves a list of all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @Operation(summary = "Get flight by ID", description = "Retrieves a flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the flight", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{flightId}")
    public Flight getFlightById(
            @Parameter(description = "ID of the flight to be retrieved", required = true) @PathVariable Long flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }

    @Operation(summary = "Create a new flight", description = "Creates a new flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flight successfully created", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Flight createFlight(@Parameter(description = "Flight object to be created", required = true) @RequestBody Flight flight) {
        logger.info("Creating new flight: " + flight);
        return flightService.createFlight(flight);
    }

    @Operation(summary = "Update an existing flight", description = "Updates an existing flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight successfully updated", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{flightId}")
    public Flight updateFlight(
            @Parameter(description = "ID of the flight to be updated", required = true) @PathVariable Long flightId,
            @Parameter(description = "Updated flight object", required = true) @RequestBody Flight flight) throws Exception {
        return flightService.updateFlight(flightId, flight);
    }

    @Operation(summary = "Delete a flight", description = "Deletes a flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(
            @Parameter(description = "ID of the flight to be deleted", required = true) @PathVariable Long flightId) {
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