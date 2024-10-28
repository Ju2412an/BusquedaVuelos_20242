package co.edu.udea.codefactory.sitas.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;
import co.edu.udea.codefactory.sitas.model.FlightDetails;
import co.edu.udea.codefactory.sitas.service.FlightService;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam("departureDate") String departureDate,
            @RequestParam("departure") UUID departure,
            @RequestParam("arrival") UUID arrival
    ) {
        if (departure == null || arrival == null) {
            return ResponseEntity.badRequest().body(null); // Manejar UUID nulos
        }

        try {
            LocalDate parsedDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Airport departureAirport = new Airport();
            Airport arrivalAirport = new Airport();
            departureAirport.setUuid(departure);
            arrivalAirport.setUuid(arrival);

            List<Flight> flights = flightService.findFlights(parsedDate, departureAirport, arrivalAirport);
            if (flights.isEmpty()) {
                logger.warn("No flights found for the specified criteria");
                return ResponseEntity.noContent().build(); // Sin vuelos encontrados
            }
            
            return ResponseEntity.ok(flights);
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format", e);
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/details/{flightId}")
    public ResponseEntity<FlightDetails> getFlightDetails(@PathVariable UUID flightId) {
        FlightDetails flightDetails = flightService.getFlightDetails(flightId);
        return ResponseEntity.ok(flightDetails);
    }

    @Setter
    @Getter
    public static class CustomBody {
        private String departureDate;
        private Airport arrivalAirport;
        private Airport departureAirport;
    }
}