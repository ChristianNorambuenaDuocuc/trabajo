package com.duoc.backend;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.List;

import com.duoc.backend.medication.MedicationController;
import com.duoc.backend.medication.MedicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import com.duoc.backend.medication.Medication;

@WebMvcTest(MedicationController.class)
@WithMockUser
class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔹 GET ALL
    @Test
    void shouldReturnAllMedications() throws Exception {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationService.getAllMedications())
                .thenReturn(List.of(medication));

        mockMvc.perform(get("/medication"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    // 🔹 GET BY ID
    @Test
    void shouldReturnMedicationById() throws Exception {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationService.getMedicationById(1L))
                .thenReturn(medication);

        mockMvc.perform(get("/medication/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 🔹 POST
    @Test
    void shouldSaveMedication() throws Exception {
        Medication medication = new Medication();
        medication.setId(1L);

        when(medicationService.saveMedication(any(Medication.class)))
                .thenReturn(medication);

        mockMvc.perform(post("/medication")
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 🔹 DELETE
    @Test
    void shouldDeleteMedication() throws Exception {
        doNothing().when(medicationService).deleteMedication(1L);

        mockMvc.perform(delete("/medication/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
