package com.ducalme.app.repositories;

import com.ducalme.app.models.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Integer> {

}
