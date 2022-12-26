package ru.job4j.passport.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Firstname must not be blank")
    private String firstName;
    @NotBlank(message = "Surname must not be blank")
    private String secondName;
    private LocalDate birthDate;

    public Owner() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Owner owner = (Owner) o;
        return id == owner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Owner{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", secondName='" + secondName + '\''
                + ", birthDate=" + birthDate
                + '}';
    }
}
