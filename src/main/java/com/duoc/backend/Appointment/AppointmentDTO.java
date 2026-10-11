package com.duoc.backend.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {

    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String veterinarian;
}