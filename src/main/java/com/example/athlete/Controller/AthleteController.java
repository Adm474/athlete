package com.example.athlete.Controller;

import com.example.athlete.Entity.Athlete;
import com.example.athlete.Service.AthleteService;
import com.example.athlete.Controller.Dto.AthleteDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        return ResponseEntity.ok(athleteService.getAllAthletes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.getAthleteById(id));
    }

    @GetMapping("/sport/{sportId}")
    public ResponseEntity<List<Athlete>> getAthletesBySport(@PathVariable Long sportId) {
        return ResponseEntity.ok(athleteService.getAthletesBySportId(sportId));
    }

    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Athlete>> getAthletesByNationality(@PathVariable String nationality) {
        return ResponseEntity.ok(athleteService.getAthletesByNationality(nationality));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Athlete>> searchAthletes(@RequestParam String lastName) {
        return ResponseEntity.ok(athleteService.searchAthletesByLastName(lastName));
    }

    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@Valid @RequestBody AthleteDto athleteDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(athleteService.createAthlete(athleteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Athlete> updateAthlete(
            @PathVariable Long id,
            @Valid @RequestBody AthleteDto athleteDto) {
        return ResponseEntity.ok(athleteService.updateAthlete(id, athleteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
        return ResponseEntity.noContent().build();
    }
}