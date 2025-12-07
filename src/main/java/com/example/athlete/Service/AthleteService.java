package com.example.athlete.Service;

import com.example.athlete.Entity.Athlete;
import com.example.athlete.Entity.Sport;
import com.example.athlete.Repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final SportService sportService;

    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    public Athlete getAthleteById(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete not found with id: " + id));
    }

    public List<Athlete> getAthletesBySportId(Long sportId) {
        return athleteRepository.findBySportId(sportId);
    }

    public List<Athlete> getAthletesByNationality(String nationality) {
        return athleteRepository.findByNationality(nationality);
    }

    public List<Athlete> searchAthletesByLastName(String lastName) {
        return athleteRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Athlete createAthlete(Athlete athlete, Long sportId) {
        Sport sport = sportService.getSportById(sportId);
        athlete.setSport(sport);
        return athleteRepository.save(athlete);
    }

    public Athlete updateAthlete(Long id, Athlete athleteDetails, Long sportId) {
        Athlete athlete = getAthleteById(id);

        athlete.setFirstName(athleteDetails.getFirstName());
        athlete.setLastName(athleteDetails.getLastName());
        athlete.setBirthDate(athleteDetails.getBirthDate());
        athlete.setNationality(athleteDetails.getNationality());
        athlete.setYearsOfExperience(athleteDetails.getYearsOfExperience());

        if (sportId != null) {
            Sport sport = sportService.getSportById(sportId);
            athlete.setSport(sport);
        }

        return athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id) {
        Athlete athlete = getAthleteById(id);
        athleteRepository.delete(athlete);
    }
}