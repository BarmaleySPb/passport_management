package ru.job4j.passport.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OwnerDTO {
    @NotBlank
    String firstName;
    @NotBlank
    String secondName;
}
