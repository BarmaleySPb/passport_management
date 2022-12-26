package ru.job4j.passport.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueSeriesAndNumber", columnNames = {"series", "number"})
})
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must not be null")
    private long id;
    @Min(value = 1, message = "Passport series must be in range from 0001 "
            + "to 9999 and consist of four digits")
    @Max(9999)
    private int series;
    @Min(value = 1, message = "Passport number must be in range from 000001 "
            + "to 999999 and consist of six digits")
    @Max(999999)
    private int number;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
    @OneToOne
    private Owner owner;

    public Passport() {
    }

    public static Passport of(int series, int number, Owner owner) {
        Passport passport = new Passport();
        passport.setSeries(series);
        passport.setNumber(number);
        LocalDate now = LocalDate.now();
        passport.setOwner(owner);
        passport.setDateOfIssue(now);
        passport.setExpiryDate(setExpiryDate(now, owner.getBirthDate()));
        return passport;
    }

    private static LocalDate setExpiryDate(LocalDate now, LocalDate birthDate) {
        LocalDate expDate;
        LocalDate minusTwenty = now.minusYears(20);
        LocalDate minusFortyFive = now.minusYears(45);
        if (birthDate.isAfter(minusTwenty)) {
            expDate = birthDate.plusYears(20);
        } else if (birthDate.isBefore(minusTwenty) && birthDate.isAfter(minusFortyFive)) {
            expDate = birthDate.plusYears(45);
        } else {
            expDate = now.plusYears(199);
        }
        return expDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id && series == passport.series && number == passport.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, series, number);
    }

    @Override
    public String toString() {
        return "Passport{"
                + "id=" + id
                + ", series='" + series + '\''
                + ", number='" + number + '\''
                + ", dateOfIssue=" + dateOfIssue
                + ", expiryDate=" + expiryDate
                + '}';
    }
}
