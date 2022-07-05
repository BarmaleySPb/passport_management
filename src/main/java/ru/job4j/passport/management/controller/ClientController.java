package ru.job4j.passport.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.model.PassportDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final RestTemplate restTemplate;

    @Value("${endpoint}")
    private String endpoint;

    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/find")
    public Iterable<Passport> findAll() {
        return restTemplate.exchange(
                endpoint + "/find",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @GetMapping("/find/{series}")
    public Iterable<Passport> findBySeries(@PathVariable String series) {
        return restTemplate.exchange(endpoint + "/find/" + series,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @PostMapping("/save")
    public ResponseEntity<PassportDTO> create(@Valid @RequestBody PassportDTO passportDTO) {
        PassportDTO rsl = restTemplate.postForObject(endpoint + "/save",
                passportDTO, PassportDTO.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody PassportDTO passportDTO) {
        restTemplate.put(endpoint + "/update/" + id, passportDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        restTemplate.delete(endpoint + "/delete/{id}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unavailable")
    public Iterable<Passport> unavailable() {
        return restTemplate.exchange(
                endpoint + "/unavailable",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @GetMapping("/find-replaceable")
    public Iterable<Passport> needReplace() {
        return restTemplate.exchange(
                endpoint + "/find-replaceable",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }
}