package com.omercangoktas.flight_search_api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omercangoktas.flight_search_api.exception.NotFoundException;
import com.omercangoktas.flight_search_api.model.Airport;
import com.omercangoktas.flight_search_api.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/airports")
@Tag(name = "Airport API", description = "API for managing airports")
public class AirportController {

    private final AirportService airportService;

    private static final Logger logger = LoggerFactory.getLogger(AirportController.class);

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Operation(summary = "Get all airports", description = "Retrieves a list of all airports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of airports", content = @Content(schema = @Schema(implementation = Airport.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @Operation(summary = "Get airport by ID", description = "Retrieves an airport by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the airport", content = @Content(schema = @Schema(implementation = Airport.class))),
            @ApiResponse(responseCode = "404", description = "Airport not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{airportId}")
    public Airport getAirportById(
            @Parameter(description = "ID of the airport to be retrieved", required = true) @PathVariable Long airportId) throws Exception {
        return airportService.getAirportById(airportId);
    }

    @Operation(summary = "Create a new airport", description = "Creates a new airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Airport successfully created", content = @Content(schema = @Schema(implementation = Airport.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Airport createAirport(@Parameter(description = "Airport object to be created", required = true) @RequestBody Airport airport) {
        logger.info("Creating new airport: " + airport);
        return airportService.createAirport(airport);
    }

    @Operation(summary = "Delete an airport", description = "Deletes an airport by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Airport not found"),
            @ApiResponse(responseCode = "409", description = "Conflict, airport cannot be deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{airportId}")
    public ResponseEntity<String> deleteAirport(
            @Parameter(description = "ID of the airport to be deleted", required = true) @PathVariable Long airportId) {
        try {
            airportService.deleteAirportById(airportId);
            return ResponseEntity.ok("Airport with id " + airportId + " deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Update an existing airport", description = "Updates an existing airport by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport successfully updated", content = @Content(schema = @Schema(implementation = Airport.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Airport not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{airportId}")
    public Airport updateAirport(
            @Parameter(description = "ID of the airport to be updated", required = true) @PathVariable Long airportId,
            @Parameter(description = "Updated airport object", required = true) @RequestBody Airport airport) throws Exception {
        return airportService.updateAirport(airportId, airport);
    }
}