package com.example.athlete.Repository;

import com.example.athlete.Entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findBySportId(Long sportId);
    List<Athlete> findByNationality(String nationality);
    List<Athlete> findByLastNameContainingIgnoreCase(String lastName);
}