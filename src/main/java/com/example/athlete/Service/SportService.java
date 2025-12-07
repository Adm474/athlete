package com.example.athlete.Service;

import com.example.athlete.Entity.Sport;
import com.example.athlete.Repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SportService {

    private final SportRepository sportRepository;

    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Sport getSportById(Long id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with id: " + id));
    }

    public Sport getSportByName(String name) {
        return sportRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Sport not found with name: " + name));
    }

    public Sport createSport(Sport sport) {
        if (sportRepository.existsByName(sport.getName())) {
            throw new RuntimeException("Sport with name '" + sport.getName() + "' already exists");
        }
        return sportRepository.save(sport);
    }

    public Sport updateSport(Long id, Sport sportDetails) {
        Sport sport = getSportById(id);

        if (!sport.getName().equals(sportDetails.getName()) &&
                sportRepository.existsByName(sportDetails.getName())) {
            throw new RuntimeException("Sport with name '" + sportDetails.getName() + "' already exists");
        }

        sport.setName(sportDetails.getName());
        sport.setDescription(sportDetails.getDescription());
        sport.setIsTeamSport(sportDetails.getIsTeamSport());

        return sportRepository.save(sport);
    }

    public void deleteSport(Long id) {
        Sport sport = getSportById(id);
        sportRepository.delete(sport);
    }
}