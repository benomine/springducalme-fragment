package com.ducalme.app.services;

import com.ducalme.app.repositories.PaiementRepository;
import com.ducalme.app.models.Paiement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public List<Paiement> findAll() {
        return (List<Paiement>) paiementRepository.findAll();
    }

    public Optional<Paiement> findById(Integer id) {
        return paiementRepository.findById(id);
    }

    public Paiement save(Paiement locataire) {
        return paiementRepository.save(locataire);
    }

    public void deleteById(Integer id) {
        paiementRepository.deleteById(id);
    }
}
