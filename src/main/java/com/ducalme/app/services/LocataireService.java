package com.ducalme.app.services;

import com.ducalme.app.repositories.LocataireRepositery;
import com.ducalme.app.models.Locataire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocataireService {

    private final LocataireRepositery locataireRepositery;

    public LocataireService(LocataireRepositery locataireRepositery) {
        this.locataireRepositery = locataireRepositery;
    }

    public List<Locataire> findAll() {
        return (List<Locataire>) locataireRepositery.findAll();
    }

    public Optional<Locataire> findById(Integer id) {
        return locataireRepositery.findById(id);
    }

    public Locataire save(Locataire locataire) {
        return locataireRepositery.save(locataire);
    }

    public boolean update(Integer id, Locataire locataire) throws Exception {
        Optional<Locataire> locataireOptional = locataireRepositery.findById(id);
        if(locataireOptional.isPresent()) {
            Locataire temp = locataireOptional.get();
            temp.setNom(locataire.getNom());
            temp.setPrenom(locataire.getPrenom());
            temp.setTelephone(locataire.getTelephone());
            locataireRepositery.save(temp);
            return true;
        }
        else {
            throw new Exception("Impossible de mettre à jour le locataire.");
        }
    }

    public void deleteById(Integer id) {
        locataireRepositery.deleteById(id);
    }


}
