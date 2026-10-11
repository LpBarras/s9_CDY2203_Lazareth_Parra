package com.duoc.backend.patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    private String name;
    private String species;
    private String breed;
    private int age;
    private String owner;
}