package ru.job4j.passport.management.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.passport.management.model.Passport;

import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {

    Iterable<Passport> findBySeries(String series);
    Optional<Passport> findBySeriesAndNumber(String series, String number);
    Optional<Passport> findById(long id);

    @Query(value = "select * from passport p where p.expiry_date < current_date",
            nativeQuery = true)
    Iterable<Passport> getExpired();

    @Query(value = "select * from passport where expiry_date between"
            + " current_date and current_date + interval '3 month'", nativeQuery = true)
    Iterable<Passport> getForReplace();

    @Modifying
    @Query(value = "update passport set series = :series, number = :number where id = :id", nativeQuery = true)
    void updatePassport(@Param("series") String series, @Param("number") String number, @Param("id") long id);
}
