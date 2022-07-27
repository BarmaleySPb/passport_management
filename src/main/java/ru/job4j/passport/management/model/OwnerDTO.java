package ru.job4j.passport.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OwnerDTO {
    @NotBlank(message = "Firstname must not be blank")
    private String firstName;
    @NotBlank(message = "Surname must not be blank")
    private String secondName;
}
