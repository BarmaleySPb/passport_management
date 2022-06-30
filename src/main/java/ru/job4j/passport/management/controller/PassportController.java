package ru.job4j.passport.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.model.PassportDTO;
import ru.job4j.passport.management.service.PassportService;

import javax.validation.Valid;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/find")
    public Iterable<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/find/{series}")
    public Iterable<Passport> findBySeries(@PathVariable String series) {
        return passportService.findBySeries(series);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> create(@Valid @RequestBody PassportDTO passportDTO) {
        checkSeriesAndNumber(passportDTO);
        Passport passport = Passport.of(passportDTO.getSeries(), passportDTO.getNumber());
        this.passportService.save(passport);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody PassportDTO passportDTO) {
        checkId(id);
        checkSeriesAndNumber(passportDTO);
        passportService.updatePassport(passportDTO.getSeries(), passportDTO.getNumber(), id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        checkId(id);
        Passport passport = new Passport();
        passport.setId(id);
        passportService.delete(passport);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unavailable")
    public Iterable<Passport> unavailable() {
        return passportService.getExpired();
    }

    @GetMapping("/find-replaceable")
    public Iterable<Passport> needReplace() {
        return passportService.getForReplace();
    }

    private void checkId(long id) {
        if (passportService.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Passport whit id: " + id + " not exists");
        }
    }

    private void checkSeriesAndNumber(PassportDTO passportDTO) {
        if (passportService.findBySeriesAndNumber(passportDTO.getSeries(), passportDTO.getNumber()).isPresent()) {
            throw new IllegalArgumentException("Passport with series: " + passportDTO.getSeries()
                    + " and number: " + passportDTO.getNumber() + " already exist");
        }
    }
}
