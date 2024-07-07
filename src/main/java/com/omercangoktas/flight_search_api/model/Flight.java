package com.omercangoktas.flight_search_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "A flight entity")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The flight ID", example = "1")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Schema(description = "The departure date and time", example = "2020-07-05T18:00:00.000Z")
    private LocalDateTime departureDateTime;
    
    @Schema(description = "The arrival date and time", example = "2020-07-05T23:30:14.830Z")
    private LocalDateTime arrivalDateTime;
    
    @Schema(description = "The price of the flight", example = "150.0")
    private double price;

    public Flight() {
        
    }

    public Flight(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime,
                    LocalDateTime arrivalDateTime, double price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.price = price;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight [flightId=" + flightId + ", departureAirport=" + departureAirport + ", arrivalAirport="
                + arrivalAirport + ", departureDateTime=" + departureDateTime + ", arrivalDateTime=" + arrivalDateTime
                + ", price=" + price + "]";
    }
}