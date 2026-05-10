package com.duoc.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.duoc.backend.patient.Patient;
import com.duoc.backend.patient.PatientController;
import com.duoc.backend.patient.PatientService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    // 🔹 Test greetings
    @Test
    void deberiaSaludar() throws Exception {

        mockMvc.perform(get("/patient/register")
                .param("name", "Chris"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello {Chris}"));
    }

    // 🔹 Test obtener todos
    @Test
    void deberiaRetornarListaPacientes() throws Exception {

        Patient p1 = new Patient();
        p1.setId(1L);
        p1.setName("Juan");

        when(patientService.getAllPatients()).thenReturn(List.of(p1));

        mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Juan"));
    }

    // 🔹 Test por ID
    @Test
    void deberiaRetornarPacientePorId() throws Exception {

        Patient p = new Patient();
        p.setId(1L);
        p.setName("Pedro");

        when(patientService.getPatientById(1L)).thenReturn(p);

        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pedro"));
    }

    // 🔹 Test guardar
    @Test
    void deberiaGuardarPaciente() throws Exception {

        Patient p = new Patient();
        p.setId(1L);
        p.setName("Ana");

        when(patientService.savePatient(any(Patient.class))).thenReturn(p);

        mockMvc.perform(post("/patient")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Ana"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    // 🔹 Test eliminar
    @Test
    void deberiaEliminarPaciente() throws Exception {

        doNothing().when(patientService).deletePatient(1L);

        mockMvc.perform(delete("/patient/1"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).deletePatient(1L);
    }
}