package com.duoc.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.backend.medication.MedicationRepository;
import com.duoc.backend.medication.MedicationService;
import com.duoc.backend.medication.Medication;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    // 🔹 GET ALL
    @Test
    void shouldReturnAllMedications() {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationRepository.findAll()).thenReturn(List.of(medication));

        List<Medication> result = medicationService.getAllMedications();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // 🔹 GET BY ID (existe)
    @Test
    void shouldReturnMedicationWhenExists() {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationRepository.findById(1L))
                .thenReturn(Optional.of(medication));

        Medication result = medicationService.getMedicationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // 🔹 GET BY ID (no existe)
    @Test
    void shouldReturnNullWhenNotExists() {
        when(medicationRepository.findById(1L))
                .thenReturn(Optional.empty());

        Medication result = medicationService.getMedicationById(1L);

        assertNull(result);
    }

    // 🔹 SAVE
    @Test
    void shouldSaveMedication() {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationRepository.save(any(Medication.class)))
                .thenReturn(medication);

        Medication result = medicationService.saveMedication(medication);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // 🔹 DELETE
    @Test
    void shouldDeleteMedication() {
        medicationService.deleteMedication(1L);

        verify(medicationRepository).deleteById(1L);
    }
}