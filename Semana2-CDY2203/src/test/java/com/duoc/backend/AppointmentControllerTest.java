package com.duoc.backend;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.List;

import com.duoc.backend.appointment.Appointment;
import com.duoc.backend.appointment.AppointmentController;
import com.duoc.backend.appointment.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(AppointmentController.class)
@WithMockUser
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    //GET ALL
    @Test
    void shouldReturnAllAppointments() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.getAllAppointments()).thenReturn(List.of(appointment));

        mockMvc.perform(get("/appointment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    // GET BY ID
    @Test
    void shouldReturnAppointmentById() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        mockMvc.perform(get("/appointment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // POST
    @Test
    void shouldSaveAppointment() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.saveAppointment(any(Appointment.class)))
                .thenReturn(appointment);

        mockMvc.perform(post("/appointment")
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // DELETE
    @Test
    void shouldDeleteAppointment() throws Exception {
        doNothing().when(appointmentService).deleteAppointment(1L);

        mockMvc.perform(delete("/appointment/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}