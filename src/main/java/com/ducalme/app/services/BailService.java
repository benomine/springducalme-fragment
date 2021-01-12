package com.ducalme.app.services;

import com.ducalme.app.repositories.BailRepository;
import com.ducalme.app.models.Bail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BailService {

    private final BailRepository bailRepository;

    public BailService(BailRepository bailRepository) {
        this.bailRepository = bailRepository;
    }

    public List<Bail> findAll() {
        return (List<Bail>) bailRepository.findAll();
    }

    public Optional<Bail> findById(Integer id) {
        return bailRepository.findById(id);
    }

    public Bail save(Bail bail) {
        return bailRepository.save(bail);
    }

    public void deleteById(Integer id) {
        bailRepository.deleteById(id);
    }
}
