package ru.job4j.passport.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class PassportDTO {
    @Pattern(regexp = "^\\d{4}$", message = "Passport series must consist of only four digits")
    private String series;
    @Pattern(regexp = "^\\d{6}$", message = "Passport number must consist of only six digits")
    private String number;
    Owner owner;
}
