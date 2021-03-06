package com.ducalme.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Bail extends AbstractEntity {
    @ManyToOne
    private Locataire locataire;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSignature;
    private double duree;
}
