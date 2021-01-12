package com.ducalme.app.repositories;

import com.ducalme.app.models.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocataireRepositery extends JpaRepository<Locataire, Integer> {

}
