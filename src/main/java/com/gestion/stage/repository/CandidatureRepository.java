package com.ahlem.locationVoiture.repository;

import com.ahlem.locationVoiture.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
}
