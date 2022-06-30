package ru.job4j.passport.management.controller;

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

    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public Iterable<Passport> findAll() {
        return restTemplate.exchange(
                "http://localhost:8080/passport/find",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @GetMapping("/find/{series}")
    public Iterable<Passport> findBySeries(@PathVariable String series) {
        return restTemplate.exchange("http://localhost:8080/passport/find/" + series,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @PostMapping("/save")
    public ResponseEntity<PassportDTO> create(@Valid @RequestBody PassportDTO passportDTO) {
        PassportDTO rsl = restTemplate.postForObject("http://localhost:8080/passport/save",
                passportDTO, PassportDTO.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody PassportDTO passportDTO) {
        restTemplate.put("http://localhost:8080/passport/update/" + id, passportDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        restTemplate.delete("http://localhost:8080/passport/delete/{id}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unavailable")
    public Iterable<Passport> unavailable() {
        return restTemplate.exchange(
                "http://localhost:8080/passport/unavailable",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }

    @GetMapping("/find-replaceable")
    public Iterable<Passport> needReplace() {
        return restTemplate.exchange(
                "http://localhost:8080/passport/find-replaceable",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<Passport>>() { }).getBody();
    }
}