package com.ducalme.app.repositories;

import com.ducalme.app.models.Bail;
import com.ducalme.app.models.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BailRepository extends JpaRepository<Bail, Integer> {
    public Locataire searchBailById(Integer id);
}
