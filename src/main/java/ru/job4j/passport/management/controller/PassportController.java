package ru.job4j.passport.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.management.model.OwnerDTO;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.model.PassportDTO;
import ru.job4j.passport.management.service.OwnerService;
import ru.job4j.passport.management.service.PassportService;

import javax.validation.Valid;
import java.util.List;

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
        return new ResponseEntity<>(passports, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@Valid @RequestBody PassportDTO passportDTO) {
        Passport passport = Passport.of(
                passportDTO.getSeries(),
                passportDTO.getNumber(),
                passportDTO.getOwner()
        );
        passportService.save(passport);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @Valid @RequestBody OwnerDTO ownerDTO) {
        Passport passportForUpdate;
        passportForUpdate = passportService.findById(id);
        String firstName = ownerDTO.getFirstName();
        String secondName = ownerDTO.getSecondName();
        long ownerId = passportForUpdate.getOwner().getId();
        ownerService.updateData(firstName, secondName, ownerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Passport passport = new Passport();
        passport.setId(id);
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
