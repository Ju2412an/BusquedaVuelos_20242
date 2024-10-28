import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.codefactory.sitas.controller.FlightController;
import co.edu.udea.codefactory.sitas.exception.FlightNotFoundException;

public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    void testGetFlightDetails_FlightNotFound() throws Exception {
        UUID flightId = UUID.randomUUID();

        when(flightService.getFlightDetails(flightId)).thenThrow(new FlightNotFoundException("Flight not found"));

        mockMvc.perform(get("/flights/{id}", flightId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Flight not found"));
    }
}
