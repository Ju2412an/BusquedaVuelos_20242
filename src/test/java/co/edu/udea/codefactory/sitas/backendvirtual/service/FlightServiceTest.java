package co.edu.udea.codefactory.sitas.backendvirtual.service;

import co.edu.udea.codefactory.sitas.exception.FlightNotFoundException;
import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;
import co.edu.udea.codefactory.sitas.model.FlightDetails;
import co.edu.udea.codefactory.sitas.repository.FlightRepository;
import co.edu.udea.codefactory.sitas.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Simular datos de vuelo
        flight = new Flight();
        flight.setId(UUID.randomUUID());
        flight.setFlightNumber(12345L);
        flight.setDepartureAirport(new Airport(UUID.randomUUID(), "JFK", "John F. Kennedy International"));
        flight.setArrivalAirport(new Airport(UUID.randomUUID(), "LAX", "Los Angeles International"));
        flight.setPrice(500.0);
    }

    @Test
    void testGetFlightDetails_Success() {
        // Simula el comportamiento del repositorio
        when(flightRepository.findById(any(UUID.class))).thenReturn(Optional.of(flight));

        // Ejecuta el método a probar
        FlightDetails flightDetails = flightService.getFlightDetails(flight.getId());

        // Verifica los resultados
        assertNotNull(flightDetails);
        assertEquals("12345", flightDetails.getFlightNumber());
        assertEquals("JFK", flightDetails.getDepartureAirportName());
        assertEquals(500.0, flightDetails.getPrice());
    }

    @Test
    void testGetFlightDetails_FlightNotFound() {
        // Simula el comportamiento del repositorio cuando el vuelo no se encuentra
        when(flightRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Verifica que se lance la excepción esperada
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightDetails(UUID.randomUUID()));
    }
}