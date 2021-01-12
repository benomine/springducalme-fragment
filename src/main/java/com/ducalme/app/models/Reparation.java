package com.ducalme.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reparation extends AbstractEntity {
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinalisation;
    private String status;
}
