package com.example.trainreservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "Train Reservation REST API");
        response.put("status", "UP");
        response.put("database", "SQL (H2 / JPA)");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("GET /api/reservations", "List all train reservations");
        endpoints.put("GET /api/reservations/{id}", "Get reservation by ID");
        endpoints.put("POST /api/reservations", "Create new reservation (JSON body)");
        endpoints.put("PUT /api/reservations/{id}", "Update existing reservation (JSON body)");
        endpoints.put("DELETE /api/reservations/{id}", "Cancel and delete reservation by ID");
        endpoints.put("GET /h2-console", "H2 Web SQL Console (JDBC URL: jdbc:h2:mem:traindb)");

        response.put("endpoints", endpoints);
        return response;
    }
}
