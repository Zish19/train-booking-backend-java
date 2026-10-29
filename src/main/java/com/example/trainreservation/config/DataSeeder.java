package com.example.trainreservation.config;

import com.example.trainreservation.model.Reservation;
import com.example.trainreservation.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ReservationRepository repository;

    public DataSeeder(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                new Reservation("Rahul Sharma", "12951", "Rajdhani Express", "Mumbai Central", "New Delhi", "2026-11-15", "B3-24", "AC 3 Tier", "CONFIRMED"),
                new Reservation("Priya Patel", "12002", "Bhopal Shatabdi", "New Delhi", "Bhopal", "2026-11-20", "C2-14", "AC Chair Car", "CONFIRMED"),
                new Reservation("Amit Verma", "12260", "Sealdah Duronto", "New Delhi", "Sealdah", "2026-11-25", "A1-08", "AC 2 Tier", "CONFIRMED")
            ));
        }
    }
}
