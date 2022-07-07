package ru.job4j.passport.management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.repository.OwnerRepository;
import ru.job4j.passport.management.repository.PassportRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PassportService {

    private final PassportRepository passportRepository;
    private final OwnerRepository ownerRepository;

    public PassportService(PassportRepository passportRepository, OwnerRepository ownerRepository) {
        this.passportRepository = passportRepository;
        this.ownerRepository = ownerRepository;
    }

    public Iterable<Passport> findAll() {
        return passportRepository.findAll();
    }

    public Iterable<Passport> findBySeries(int series) {
        return passportRepository.findBySeries(series);
    }

    public Optional<Passport> findBySeriesAndNumber(int series, int number) {
        return passportRepository.findBySeriesAndNumber(series, number);
    }

    public Passport findById(long id) {
        return passportRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passport with id: " + id + " not found")
        );
    }

    public void save(Passport passport) {
        int series = passport.getSeries();
        int number = passport.getNumber();
        if (findBySeriesAndNumber(series, number).isPresent()) {
            throw new IllegalArgumentException(
                    "Passport " + series + " " + number + " already exists"
            );
        }
        passportRepository.save(passport);
    }

    public void delete(Passport passport) {
        Passport passportForDelete = findById(passport.getId());
        passportRepository.delete(passportForDelete);
        ownerRepository.delete(passportForDelete.getOwner());
    }

    public Iterable<Passport> findExpiredPassports() {
        return passportRepository.findExpiredPassports();
    }

    public Iterable<Passport> findPassportsForReplace() {
        return passportRepository.findPassportsForReplace();
    }
}
