package com.duoc.backend;

import com.duoc.backend.care.Care;
import com.duoc.backend.care.CareController;
import com.duoc.backend.care.CareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.duoc.backend.care.CareDTO;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class CareControllerTest {

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private CareController careController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllCares() {
        var cares = Arrays.asList(new Care("Consulta", 50.0), new Care("Vacuna", 30.0));
        when(careRepository.findAll()).thenReturn(cares);

        assertEquals(2, careController.getAllCares().size());
        verify(careRepository).findAll();
    }

    @Test
    void shouldGetCareById() {
        Care care = new Care("Consulta", 50.0);
        care.setId(1L);
        when(careRepository.findById(1L)).thenReturn(Optional.of(care));

        assertSame(care, careController.getCareById(1L));
    }

    @Test
void shouldSaveCare() {
    CareDTO dto = new CareDTO();
    dto.setName("Consulta");
    dto.setCost(50.0);

    Care care = new Care("Consulta", 50.0);

    when(careRepository.save(any(Care.class))).thenReturn(care);

    Care result = careController.saveCare(dto);

    assertSame(care, result);

    verify(careRepository).save(argThat(c ->
            c.getName().equals(dto.getName())
            && c.getCost().equals(dto.getCost())
    ));
}

    @Test
    void shouldDeleteCare() {
        careController.deleteCare(1L);

        verify(careRepository).deleteById(1L);
    }
}
