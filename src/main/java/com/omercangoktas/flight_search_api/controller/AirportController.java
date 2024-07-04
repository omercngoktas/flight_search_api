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

@RestController
@RequestMapping(value = "/airports")
public class AirportController {

    private AirportService airportService;

    private static final Logger logger = LoggerFactory.getLogger(Airport.class);

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(value = "/{airportId}")
    public Airport getAirportById(@PathVariable Long airportId) throws Exception {
        logger.info("Retrieving airport with id: " + airportId);
        return airportService.getAirportById(airportId);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        logger.info("Creating new airport: " + airport);
        return airportService.createAirport(airport);
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long airportId) {
        try {
            airportService.deleteAirportById(airportId);
            return ResponseEntity.ok("Airport with id " + airportId + " deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{airportId}")
    public Airport updateAirport(@PathVariable Long airportId,
                            @RequestBody Airport airport) throws Exception {
        return airportService.updateAirport(airportId, airport);
    }
}
