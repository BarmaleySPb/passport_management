package ru.job4j.passport.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.passport.management.model.OwnerDTO;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.model.PassportDTO;
import ru.job4j.passport.management.service.OwnerService;
import ru.job4j.passport.management.service.PassportService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;
    private final OwnerService ownerService;

    public PassportController(PassportService passportService, OwnerService ownerService) {
        this.passportService = passportService;
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public Iterable<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/find/{series}")
    public ResponseEntity<Iterable<Passport>> findBySeries(@PathVariable int series) {
        List<Passport> passports = passportService.findBySeries(series);
        if (passports.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Passports with series: " + series + " not found.");
        }
        return new ResponseEntity<>(passports, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@Valid @RequestBody PassportDTO passportDTO) {
        Passport passport = Passport.of(
                Integer.parseInt(passportDTO.getSeries()),
                Integer.parseInt(passportDTO.getNumber()), passportDTO.getOwner()
        );
        passportService.save(passport);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @Valid @RequestBody OwnerDTO ownerDTO) {
        Passport passportForUpdate = passportService.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passport with id: " + id + " not found.")
        );
        String firstName = ownerDTO.getFirstName();
        String secondName = ownerDTO.getSecondName();
        long ownerId = passportForUpdate.getOwner().getId();
        ownerService.updateData(firstName, secondName, ownerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Passport passport = passportService.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passport with id: " + id + " not found.")
        );
        passportService.delete(passport);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unavailable")
    public Iterable<Passport> unavailable() {
        return passportService.findExpiredPassports();
    }

    @GetMapping("/find-replaceable")
    public Iterable<Passport> needReplace() {
        return passportService.findPassportsForReplace();
    }
}
