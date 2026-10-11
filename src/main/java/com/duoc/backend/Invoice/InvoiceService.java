package com.duoc.backend.Invoice;

import com.duoc.backend.care.Care;
import com.duoc.backend.care.CareRepository;
import com.duoc.backend.medication.Medication;
import com.duoc.backend.medication.MedicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.ArrayList;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private CareRepository careRepository;

    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(Invoice invoice) {
        // Validar que los medicamentos existen
        List<Medication> validMedications = StreamSupport.stream(
                medicationRepository.findAllById(
                        invoice.getMedications().stream().map(Medication::getId).collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());
        if (validMedications.size() != invoice.getMedications().size()) {
            throw new IllegalArgumentException("Algunos medicamentos no existen en la base de datos.");
        }

        // Validar que los servicios existen
        List<Care> validCares = StreamSupport.stream(
                careRepository.findAllById(
                        invoice.getCares().stream().map(Care::getId).collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());
        if (validCares.size() != invoice.getCares().size()) {
            throw new IllegalArgumentException("Algunos servicios no existen en la base de datos.");
        }

        // Calcular el costo total basado en los servicios y medicamentos asociados
        double totalCareCost = validCares.stream()
                .mapToDouble(Care::getCost)
                .sum();

        double totalMedicationCost = validMedications.stream()
                .mapToDouble(Medication::getCost)
                .sum();

        invoice.setTotalCost(totalCareCost + totalMedicationCost);

        // Guardar la factura en el repositorio
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice saveInvoice(InvoiceDTO dto) {

    Invoice invoice = new Invoice();

    invoice.setDate(dto.getDate());
    invoice.setTime(dto.getTime());
    invoice.setPatientName(dto.getPatientName());

    List<Care> cares = new ArrayList<>();
    List<Medication> medications = new ArrayList<>();

    // Buscar las atenciones existentes
    if (dto.getCares() != null) {
        for (InvoiceDTO.IdDTO item : dto.getCares()) {

            if (item == null || item.getId() == null) {
                throw new IllegalArgumentException("ID de atención inválido.");
            }

            Care care = careRepository.findById(item.getId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Atención no encontrada."));

            cares.add(care);
        }
    }

    // Buscar los medicamentos existentes
    if (dto.getMedications() != null) {
        for (InvoiceDTO.IdDTO item : dto.getMedications()) {

            if (item == null || item.getId() == null) {
                throw new IllegalArgumentException("ID de medicamento inválido.");
            }

            Medication medication = medicationRepository.findById(item.getId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Medicamento no encontrado."));

            medications.add(medication);
        }
    }

    invoice.setCares(cares);
    invoice.setMedications(medications);

    return saveInvoice(invoice);
}
}