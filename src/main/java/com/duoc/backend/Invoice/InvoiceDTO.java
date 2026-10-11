package com.duoc.backend.Invoice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class InvoiceDTO {

    private LocalDate date;
    private LocalTime time;
    private String patientName;
    private List<IdDTO> cares;
    private List<IdDTO> medications;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<IdDTO> getCares() {
        return cares;
    }

    public void setCares(List<IdDTO> cares) {
        this.cares = cares;
    }

    public List<IdDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<IdDTO> medications) {
        this.medications = medications;
    }

    public static class IdDTO {

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}