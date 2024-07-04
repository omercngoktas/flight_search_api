package com.omercangoktas.flight_search_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Airport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    
    private String city;

    private static final Logger logger = LoggerFactory.getLogger(Airport.class);

    public Airport() {
        // Default constructor required by JPA
    }

    public Airport(String city) {
        this.city = city;
        logger.info("Airport created with City: {}", this.city);
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport [airportId=" + airportId + ", city=" + city + "]";
    }
}