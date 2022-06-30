package ru.job4j.passport.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must not be null")
    private long id;
    @Pattern(regexp = "^\\d{4}$", message = "Passport series must consist of only four digits")
    private String series;
    @Pattern(regexp = "^\\d{6}$", message = "Passport number must consist of only six digits")
    private String number;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;

    public Passport() {
    }

    public static Passport of(String series, String number) {
        Passport passport = new Passport();
        passport.setSeries(series);
        passport.setNumber(number);
        passport.setDateOfIssue(LocalDate.now());
        passport.setExpiryDate(LocalDate.now().plusYears(10));
        return passport;
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
        return id == passport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
