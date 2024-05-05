package com.ahlem.locationVoiture.service;


import com.ahlem.locationVoiture.models.Candidature;
import com.ahlem.locationVoiture.repository.CandidatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureServiceImp implements CandidatureService {
    @Autowired
    private CandidatureRepository candidatureRepository;


    @Override
    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @Override
    public Candidature createCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature getCandidatureByID(Long id) {
        return candidatureRepository.findById(id).get();
    }

    @Override
    public Candidature updateCandidatureByID(Candidature candidature) {
        return candidatureRepository.saveAndFlush(candidature);
    }

    @Override
    public void deleteCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
}
