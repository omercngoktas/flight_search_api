package com.omercangoktas.flight_search_api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.service.FlightSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Flight Search API", description = "API for searching flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @Operation(summary = "Search flights", description = "Searches for flights based on departure and arrival cities, and departure and return dates.\nUsage: http://localhost:8080/flights/search?departureCity=Istanbul&arrivalCity=Ankara&departureDate=2024-07-15&returnDate=2024-07-16")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/flights/search")
    public List<Flight> searchFlights(
            @Parameter(description = "City of departure", required = true) @RequestParam String departureCity,
            @Parameter(description = "City of arrival", required = true) @RequestParam String arrivalCity,
            @Parameter(description = "Date of departure", required = true, example = "2023-12-31")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @Parameter(description = "Date of return (optional)", example = "2024-01-07")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        return flightSearchService.searchFlights(departureCity, arrivalCity, departureDate, returnDate);
    }
}