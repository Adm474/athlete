package com.example.athlete.Controller;

import com.example.athlete.Entity.Sport;
import com.example.athlete.Service.SportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
@RequiredArgsConstructor
public class SportController {

    private final SportService sportService;

    @GetMapping
    public ResponseEntity<List<Sport>> getAllSports() {
        return ResponseEntity.ok(sportService.getAllSports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSportById(@PathVariable Long id) {
        return ResponseEntity.ok(sportService.getSportById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Sport> getSportByName(@PathVariable String name) {
        return ResponseEntity.ok(sportService.getSportByName(name));
    }

    @PostMapping
    public ResponseEntity<Sport> createSport(@Valid @RequestBody Sport sport) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sportService.createSport(sport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> updateSport(
            @PathVariable Long id,
            @Valid @RequestBody Sport sport) {
        return ResponseEntity.ok(sportService.updateSport(id, sport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable Long id) {
        sportService.deleteSport(id);
        return ResponseEntity.noContent().build();
    }
}