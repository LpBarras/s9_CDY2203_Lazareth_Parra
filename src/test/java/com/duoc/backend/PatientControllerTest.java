package com.duoc.backend;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.duoc.backend.patient.PatientService;
import com.duoc.backend.patient.PatientController;
import com.duoc.backend.patient.Patient;
import com.duoc.backend.patient.PatientDTO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    public PatientControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPatients() {
        // Arrange
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setName("Firulais");
        patient1.setSpecies("Perro");
        patient1.setBreed("Labrador");
        patient1.setAge(5);
        patient1.setOwner("Juan Pérez");

        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setName("Michi");
        patient2.setSpecies("Gato");
        patient2.setBreed("Siames");
        patient2.setAge(3);
        patient2.setOwner("Ana Gómez");

        List<Patient> patients = Arrays.asList(patient1, patient2);
        when(patientService.getAllPatients()).thenReturn(patients);

        // Act
        List<Patient> result = patientController.getAllPatients();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Firulais", result.get(0).getName());
        assertEquals("Michi", result.get(1).getName());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testGetPatientById() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Firulais");
        patient.setSpecies("Perro");
        patient.setBreed("Labrador");
        patient.setAge(5);
        patient.setOwner("Juan Pérez");

        when(patientService.getPatientById(1L)).thenReturn(patient);

        // Act
        Patient result = patientController.getPatientById(1L);

        // Assert
        assertEquals("Firulais", result.getName());
        assertEquals("Perro", result.getSpecies());
        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
void testSavePatient() {
    PatientDTO dto = new PatientDTO();
    dto.setName("Firulais");
    dto.setSpecies("Perro");
    dto.setBreed("Labrador");
    dto.setAge(5);
    dto.setOwner("Juan Pérez");

    Patient patient = new Patient();
    patient.setName("Firulais");

    when(patientService.savePatient(any(Patient.class)))
            .thenReturn(patient);

    Patient result = patientController.savePatient(dto);

    assertEquals("Firulais", result.getName());

    verify(patientService).savePatient(argThat(p ->
            p.getName().equals(dto.getName())
            && p.getSpecies().equals(dto.getSpecies())
            && p.getBreed().equals(dto.getBreed())
            && p.getAge() == dto.getAge()
            && p.getOwner().equals(dto.getOwner())
    ));
}

    @Test
    void testDeletePatient() {
        // Arrange
        Long patientId = 1L;

        // Act
        patientController.deletePatient(patientId);

        // Assert
        verify(patientService, times(1)).deletePatient(patientId);
    }
}
