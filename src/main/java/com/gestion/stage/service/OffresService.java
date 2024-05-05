package com.ahlem.locationVoiture.service;

import com.ahlem.locationVoiture.models.Offres;

import java.util.List;

public interface OffresService {
    List<Offres> getAllOffres();

    Offres createOffre(Offres offres);

    Offres getOffreByID(Long id);

    Offres updateOffreByID(Offres offres);

    void deleteOffre(Long id);
}
