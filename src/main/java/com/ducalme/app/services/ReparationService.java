package com.ducalme.app.services;

import com.ducalme.app.repositories.ReparationRepository;
import com.ducalme.app.models.Reparation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReparationService {
    private final ReparationRepository reparationRepository;

    public ReparationService(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    public List<Reparation> findAll() {
        return (List<Reparation>) reparationRepository.findAll();
    }

    public Optional<Reparation> findById(Integer id) {
        return reparationRepository.findById(id);
    }

    public Reparation save(Reparation locataire) {
        return reparationRepository.save(locataire);
    }

    public void deleteById(Integer id) {
        reparationRepository.deleteById(id);
    }
}
