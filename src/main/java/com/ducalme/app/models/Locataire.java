package com.ducalme.app.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Locataire extends AbstractEntity {
    @NotNull
    public String nom;
    @NotNull
    public String prenom;
    @NotNull
    public String telephone;

    @OneToMany(mappedBy = "locataire")
    private final List<Paiement> paiementList = new ArrayList<>();

    @OneToMany(mappedBy = "locataire")
    private final List<Bail> bailList = new ArrayList<>();
}
