package co.edu.udea.codefactory.sitas.backendvirtual.controller;

import co.edu.udea.codefactory.sitas.controller.FlightController;
import co.edu.udea.codefactory.sitas.model.FlightDetails;
import co.edu.udea.codefactory.sitas.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    void testGetFlightDetails_Success() throws Exception {
        // Datos simulados
        UUID flightId = UUID.randomUUID();
        FlightDetails flightDetails = new FlightDetails("12345", "JFK", "LAX", "2024-10-15", "2024-10-16", "Boeing 737", 500.0);

        // Simula el comportamiento del servicio
        when(flightService.getFlightDetails(flightId)).thenReturn(flightDetails);

        // Realiza una solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/flights/details/" + flightId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("12345"))
                .andExpect(jsonPath("$.price").value(500.0));
    }

    @Test
    void testGetFlightDetails_FlightNotFound() throws Exception {
        UUID flightId = UUID.randomUUID();

        // Simula que el vuelo no existe
        when(flightService.getFlightDetails(flightId)).thenThrow(new RuntimeException("Flight not found"));

        // Realiza la solicitud GET y verifica que se devuelva un 404
        mockMvc.perform(get("/api/flights/details/" + flightId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}