package ru.job4j.passport.management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.repository.PassportRepository;

import java.util.Optional;

@Service
@Transactional
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Iterable<Passport> findAll() {
        return passportRepository.findAll();
    }

    public Iterable<Passport> findBySeries(String series) {
        return passportRepository.findBySeries(series);
    }

    public Optional<Passport> findBySeriesAndNumber(String series, String number) {
        return passportRepository.findBySeriesAndNumber(series, number);
    }

    public Optional<Passport> findById(long id) {
        return passportRepository.findById(id);
    }

    public void save(Passport passport) {
        passportRepository.save(passport);
    }

    public void delete(Passport passport) {
        passportRepository.delete(passport);
    }

    public Iterable<Passport> getExpired() {
        return passportRepository.getExpired();
    }

    public Iterable<Passport> getForReplace() {
        return passportRepository.getForReplace();
    }

    public void updatePassport(String series, String number, long id) {
        passportRepository.updatePassport(series, number, id);
    }
}
