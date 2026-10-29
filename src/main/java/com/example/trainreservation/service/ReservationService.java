package com.example.trainreservation.service;

import com.example.trainreservation.model.Reservation;
import com.example.trainreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return repository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    public Optional<Reservation> updateReservation(Long id, Reservation updated) {
        return repository.findById(id).map(existing -> {
            if (updated.getPassengerName() != null) {
                existing.setPassengerName(updated.getPassengerName());
            }
            if (updated.getTrainNumber() != null) {
                existing.setTrainNumber(updated.getTrainNumber());
            }
            if (updated.getTrainName() != null) {
                existing.setTrainName(updated.getTrainName());
            }
            if (updated.getSourceStation() != null) {
                existing.setSourceStation(updated.getSourceStation());
            }
            if (updated.getDestinationStation() != null) {
                existing.setDestinationStation(updated.getDestinationStation());
            }
            if (updated.getTravelDate() != null) {
                existing.setTravelDate(updated.getTravelDate());
            }
            if (updated.getSeatNumber() != null) {
                existing.setSeatNumber(updated.getSeatNumber());
            }
            if (updated.getTicketClass() != null) {
                existing.setTicketClass(updated.getTicketClass());
            }
            if (updated.getStatus() != null) {
                existing.setStatus(updated.getStatus());
            }
            return repository.save(existing);
        });
    }

    public boolean deleteReservation(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
