package co.edu.udea.codefactory.sitas.service;

import co.edu.udea.codefactory.sitas.exception.FlightNotFoundException;
import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;
import co.edu.udea.codefactory.sitas.model.FlightDetails;
import co.edu.udea.codefactory.sitas.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findFlights(LocalDate departureDate, Airport departureAirport, Airport arrivalAirport) {
        return flightRepository.findByDepartureDateEqualsAndDepartureAirportEqualsAndArrivalAirportEquals(departureDate, departureAirport, arrivalAirport);
    }

    // Metodo para obtener los detalles de un vuelo específico
    public FlightDetails getFlightDetails(UUID flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + flightId + " not found"));

        String aircraftType = "Boeing 737";  // Esto debe venir de algún otro servicio o entidad en la realidad

        return new FlightDetails(
                flight.getFlightNumber().toString(),
                flight.getDepartureAirport().getAirportName(),
                flight.getArrivalAirport().getAirportName(),
                flight.getDepartureDate().toString(),
                flight.getArrivalDate().toString(),
                aircraftType,
                flight.getPrice()
        );
    }



}
