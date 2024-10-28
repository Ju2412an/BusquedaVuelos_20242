package co.edu.udea.codefactory.sitas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udea.codefactory.sitas.exception.FlightNotFoundException;
import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;
import co.edu.udea.codefactory.sitas.model.FlightDetails;
import co.edu.udea.codefactory.sitas.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Método para buscar vuelos por fecha, aeropuerto de salida y aeropuerto de llegada
    public List<Flight> findFlights(LocalDate departureDate, Airport departureAirport, Airport arrivalAirport) {
        return flightRepository.findByDepartureDateEqualsAndDepartureAirportEqualsAndArrivalAirportEquals(departureDate, departureAirport, arrivalAirport);
    }

    // Método para obtener los detalles de un vuelo específico
    public FlightDetails getFlightDetails(UUID flightId) {
        // Buscar el vuelo por ID, lanzar excepción si no se encuentra
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + flightId + " not found"));

        // Manejar valores nulos para fechas y aeropuertos
        String departureDate = flight.getDepartureDate() != null ? flight.getDepartureDate().toString() : "No disponible";
        String arrivalDate = flight.getArrivalDate() != null ? flight.getArrivalDate().toString() : "No disponible";
        String departureAirportName = flight.getDepartureAirport() != null ? flight.getDepartureAirport().getAirportName() : "No disponible";
        String arrivalAirportName = flight.getArrivalAirport() != null ? flight.getArrivalAirport().getAirportName() : "No disponible";

        String aircraftType = "Boeing 737"; // Este valor debe ser obtenido de un servicio o entidad correspondiente en la lógica real

        // Crear y retornar los detalles del vuelo
        return new FlightDetails(
                flight.getFlightNumber().toString(),
                departureAirportName,
                arrivalAirportName,
                departureDate,
                arrivalDate,
                aircraftType,
                flight.getPrice()
        );
    }
}
