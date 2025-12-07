package com.example.athlete.Service;

import com.example.athlete.Entity.Athlete;
import com.example.athlete.Entity.Sport;
import com.example.athlete.Repository.AthleteRepository;
import com.example.athlete.Controller.Dto.AthleteDto;
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

    public Athlete createAthlete(AthleteDto athleteDto) {
        Sport sport = sportService.getSportById(athleteDto.getSportId());

        Athlete athlete = new Athlete();
        athlete.setFirstName(athleteDto.getFirstName());
        athlete.setLastName(athleteDto.getLastName());
        athlete.setBirthDate(athleteDto.getBirthDate());
        athlete.setNationality(athleteDto.getNationality());
        athlete.setYearsOfExperience(athleteDto.getYearsOfExperience());
        athlete.setSport(sport);

        return athleteRepository.save(athlete);
    }

    public Athlete updateAthlete(Long id, AthleteDto athleteDto) {
        Athlete athlete = getAthleteById(id);

        athlete.setFirstName(athleteDto.getFirstName());
        athlete.setLastName(athleteDto.getLastName());
        athlete.setBirthDate(athleteDto.getBirthDate());
        athlete.setNationality(athleteDto.getNationality());
        athlete.setYearsOfExperience(athleteDto.getYearsOfExperience());

        if (athleteDto.getSportId() != null) {
            Sport sport = sportService.getSportById(athleteDto.getSportId());
            athlete.setSport(sport);
        }

        return athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id) {
        Athlete athlete = getAthleteById(id);
        athleteRepository.delete(athlete);
    }
}