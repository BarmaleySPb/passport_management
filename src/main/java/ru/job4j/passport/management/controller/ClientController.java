package ru.job4j.passport.management.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.management.model.OwnerDTO;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.model.PassportDTO;
import ru.job4j.passport.management.service.ClientPassportService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientPassportService clientPassportService;

    public ClientController(ClientPassportService clientPassportService) {
        this.clientPassportService = clientPassportService;
    }

    @GetMapping("/find")
    public Iterable<Passport> findAll() {
        return clientPassportService.findAll();
    }

    @GetMapping("/find/{series}")
    public Iterable<Passport> findBySeries(@PathVariable int series) {
        return clientPassportService.findBySeries(series);
    }

    @PostMapping("/save")
    public ResponseEntity<PassportDTO> create(@Valid @RequestBody PassportDTO passportDTO) {
        return clientPassportService.create(passportDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @Valid @RequestBody OwnerDTO ownerDTO) {
        return clientPassportService.update(id, ownerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return clientPassportService.delete(id);
    }

    @GetMapping("/unavailable")
    public Iterable<Passport> unavailable() {
        return clientPassportService.unavailable();
    }

    @GetMapping("/find-replaceable")
    public Iterable<Passport> needReplace() {
        return clientPassportService.needReplace();
    }
}