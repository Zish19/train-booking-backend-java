package com.example.trainreservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllReservations() throws Exception {
        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void testGetReservationById() throws Exception {
        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.trainNumber", notNullValue()));
    }

    @Test
    public void testGetReservationNotFound() throws Exception {
        mockMvc.perform(get("/api/reservations/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

    @Test
    public void testCreateReservation() throws Exception {
        String payload = """
                {
                    "passengerName": "Vikram Seth",
                    "trainNumber": "12301",
                    "trainName": "Howrah Rajdhani",
                    "sourceStation": "Howrah",
                    "destinationStation": "New Delhi",
                    "travelDate": "2026-12-01",
                    "seatNumber": "B1-12",
                    "ticketClass": "AC 3 Tier",
                    "status": "CONFIRMED"
                }
                """;

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.passengerName", is("Vikram Seth")))
                .andExpect(jsonPath("$.trainNumber", is("12301")));
    }

    @Test
    public void testUpdateReservation() throws Exception {
        String updatePayload = """
                {
                    "seatNumber": "A1-01",
                    "status": "UPGRADED"
                }
                """;

        mockMvc.perform(put("/api/reservations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber", is("A1-01")))
                .andExpect(jsonPath("$.status", is("UPGRADED")));
    }

    @Test
    public void testDeleteReservation() throws Exception {
        mockMvc.perform(delete("/api/reservations/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsString("successfully cancelled and deleted")));

        mockMvc.perform(get("/api/reservations/3"))
                .andExpect(status().isNotFound());
    }
}
