package com.duoc.backend.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
public Appointment saveAppointment(@RequestBody AppointmentDTO dto) {

    Appointment appointment = new Appointment();

    appointment.setDate(dto.getDate());
    appointment.setTime(dto.getTime());
    appointment.setReason(dto.getReason());
    appointment.setVeterinarian(dto.getVeterinarian());

    return appointmentService.saveAppointment(appointment);
}

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
