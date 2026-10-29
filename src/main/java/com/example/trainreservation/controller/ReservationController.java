package com.example.trainreservation.controller;

import com.example.trainreservation.model.Reservation;
import com.example.trainreservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(service.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = service.getReservationById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        }
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", "Reservation with ID " + id + " does not exist.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation created = service.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> updated = service.updateReservation(id, reservation);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", "Cannot update. Reservation with ID " + id + " does not exist.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteReservation(@PathVariable Long id) {
        boolean deleted = service.deleteReservation(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Reservation with ID " + id + " has been successfully cancelled and deleted.");
            return ResponseEntity.ok(response);
        }
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Not Found");
        response.put("message", "Cannot delete. Reservation with ID " + id + " does not exist.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
