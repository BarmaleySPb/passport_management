package ru.job4j.passport.management.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.passport.management.model.Passport;

import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {

    Iterable<Passport> findBySeries(int series);
    Optional<Passport> findBySeriesAndNumber(int series, int number);
    Optional<Passport> findById(long id);

    @Query(value = "select * from passport p where p.expiry_date < current_date",
            nativeQuery = true)
    Iterable<Passport> findExpiredPassports();

    @Query(value = "select * from passport where expiry_date between"
            + " current_date and current_date + interval '3 month'", nativeQuery = true)
    Iterable<Passport> findPassportsForReplace();
}
