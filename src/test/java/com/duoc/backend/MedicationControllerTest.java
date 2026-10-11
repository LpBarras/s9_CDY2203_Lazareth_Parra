package com.duoc.backend;

import com.duoc.backend.medication.Medication;
import com.duoc.backend.medication.MedicationController;
import com.duoc.backend.medication.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.duoc.backend.medication.MedicationDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllMedications() {
        when(medicationService.getAllMedications()).thenReturn(
                Arrays.asList(new Medication("Antibiótico", 30.0), new Medication("Analgésico", 20.0))
        );

        assertEquals(2, medicationController.getAllMedications().size());
        verify(medicationService).getAllMedications();
    }

    @Test
    void shouldGetMedicationById() {
        Medication medication = new Medication("Antibiótico", 30.0);
        when(medicationService.getMedicationById(1L)).thenReturn(medication);

        assertSame(medication, medicationController.getMedicationById(1L));
    }

   @Test
void shouldSaveMedication() {
    MedicationDTO dto = new MedicationDTO();
    dto.setName("Antibiótico");
    dto.setCost(30.0);

    Medication medication = new Medication("Antibiótico", 30.0);

    when(medicationService.saveMedication(any(Medication.class)))
            .thenReturn(medication);

    Medication result = medicationController.saveMedication(dto);

    assertSame(medication, result);

    verify(medicationService).saveMedication(argThat(m ->
            m.getName().equals(dto.getName())
            && m.getCost().equals(dto.getCost())
    ));
}

    @Test
    void shouldDeleteMedication() {
        medicationController.deleteMedication(1L);

        verify(medicationService).deleteMedication(1L);
    }
}
