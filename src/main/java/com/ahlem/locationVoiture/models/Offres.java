package com.ahlem.locationVoiture.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Offres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "intitule")
    private String intitule;
    @Column(name = "description")
    private String description;
    @Column(name = "objectifs")
    private String objectifs;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "nb_stagiaires")
    private Integer nb_stagiaires;
    @Column(name = "specialite")
    private String specialite;
    @Column(name = "nivEtude")
    private String nivEtude;
    @JsonIgnore
    @OneToMany(mappedBy = "offres")
    private List<Candidature> candidatures = new ArrayList<Candidature>();
    @JsonIgnore
    @ManyToOne
    User user;
}