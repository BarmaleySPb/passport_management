package ru.job4j.passport.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
public class PassportDTO {
    @Min(value = 1, message = "Passport series must be in range from 0001 "
            + "to 9999 and consist of four digits")
    @Max(9999)
    private int series;
    @Min(value = 1, message = "Passport number must be in range from 000001 "
            + "to 999999 and consist of six digits")
    @Max(999999)
    private int number;
    private Owner owner;
}
