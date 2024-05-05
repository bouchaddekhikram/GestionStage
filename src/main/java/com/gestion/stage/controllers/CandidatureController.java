package com.gestion.stage.controllers;

import com.gestion.stage.models.Candidature;
import com.gestion.stage.models.User;
import com.gestion.stage.security.services.UserDetailsServiceImpl;
import com.gestion.stage.service.CandidatureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Candidature")
public class CandidatureController {
    @Autowired
    private CandidatureServiceImp offresServiceImp;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    //@PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/save/{idchef}")
    public Candidature saveProjet(@RequestBody Candidature candidature, @PathVariable Long idchef) {
        User m1= userDetailsServiceImpl.getUserByID(idchef);
        candidature.setUser(m1);
        return offresServiceImp.createCandidature(candidature);
    }


   // @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Candidature> getallProjets() {
        return offresServiceImp.getAllCandidatures();
    }


    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/getone/{id}")
    public Candidature getoneProjet(@PathVariable Long id) {
        return offresServiceImp.getCandidatureByID(id);
    }


    //@PreAuthorize( "hasRole('MANAGER') or hasRole('ADMIN')")
    @PutMapping("/update/{idCandidature}/{idUser}")
    public Candidature updateProjet(@PathVariable Long idUser, @PathVariable Long idProjet, @RequestBody Candidature candidature) {
        Candidature c1 = offresServiceImp.getCandidatureByID(idProjet);
        User m1= userDetailsServiceImpl.getUserByID(idUser);
        if (c1 != null) {
            candidature.setId(idProjet);
            candidature.setUser(m1);
            return offresServiceImp.updateCandidatureByID(candidature);
        } else {
            throw new RuntimeException("Failed ! ");
        }
    }


    @PreAuthorize( "hasRole('MANAGER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProjet(@PathVariable("id") long id) {
        try {
            offresServiceImp.deleteCandidature(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





}
