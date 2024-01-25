package com.ahlem.locationVoiture.controllers;

import com.ahlem.locationVoiture.models.Projet;
import com.ahlem.locationVoiture.models.User;
import com.ahlem.locationVoiture.security.services.UserDetailsImpl;
import com.ahlem.locationVoiture.security.services.UserDetailsServiceImpl;
import com.ahlem.locationVoiture.service.ProjetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projet")
public class ProjetController {
    @Autowired
    private ProjetServiceImp projetServiceImp;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save/{idchef}")
    public Projet saveProjet(@RequestBody Projet projet, @PathVariable Long idchef) {
        User m1= userDetailsServiceImpl.getUserByID(idchef);
        projet.setUser(m1);
        return projetServiceImp.createProjet(projet);
    }

    @GetMapping("/all")
    public List<Projet> getallProjets() {
        return projetServiceImp.getAllProjets();
    }

    @GetMapping("/getone/{id}")
    public Projet getoneProjet(@PathVariable Long id) {
        return projetServiceImp.getProjetByID(id);
    }

    @PutMapping("/update/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet projet) {
        Projet c1 = projetServiceImp.getProjetByID(id);
        if (c1 != null) {
            projet.setId(id);
            return projetServiceImp.updateProjetByID(projet);
        } else {
            throw new RuntimeException("Failed ! ");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProjet(@PathVariable("id") long id) {
        try {
            projetServiceImp.deleteProjet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





}
