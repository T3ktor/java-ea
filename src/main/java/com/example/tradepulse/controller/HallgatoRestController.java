package com.example.tradepulse.controller;

import com.example.tradepulse.entity.Hallgato;
import com.example.tradepulse.repository.HallgatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hallgatok")
@CrossOrigin(origins = "*")
public class HallgatoRestController {

    private final HallgatoRepository repository;

    public HallgatoRestController(HallgatoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Hallgato> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{eha}")
    public ResponseEntity<Hallgato> getOne(@PathVariable String eha) {
        return repository.findById(eha)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hallgato> create(@RequestBody Hallgato hallgato) {
        if (hallgato.getEha() == null || hallgato.getEha().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.save(hallgato));
    }

    @PutMapping("/{eha}")
    public ResponseEntity<Hallgato> update(@PathVariable String eha, @RequestBody Hallgato updated) {
        return repository.findById(eha)
                .map(existing -> {
                    existing.setVezeteknev(updated.getVezeteknev());
                    existing.setKeresztnev(updated.getKeresztnev());
                    existing.setEmail(updated.getEmail());
                    existing.setSzuletesiDatum(updated.getSzuletesiDatum());
                    existing.setSzakId(updated.getSzakId());
                    existing.setOsszKreditek(updated.getOsszKreditek());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{eha}")
    public ResponseEntity<Void> delete(@PathVariable String eha) {
        if (repository.existsById(eha)) {
            repository.deleteById(eha);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
