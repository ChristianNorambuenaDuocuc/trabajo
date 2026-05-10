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

import com.duoc.backend.appointment.AppointmentRepository;
import com.duoc.backend.appointment.AppointmentService;
import com.duoc.backend.appointment.Appointment;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    // 🔹 GET ALL
    @Test
    void shouldReturnAllAppointments() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findAll()).thenReturn(List.of(appointment));

        Iterable<Appointment> result = appointmentService.getAllAppointments();

        assertNotNull(result);
    }

    // 🔹 GET BY ID (existe)
    @Test
    void shouldReturnAppointmentWhenExists() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L))
                .thenReturn(Optional.of(appointment));

        Appointment result = appointmentService.getAppointmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // 🔹 GET BY ID (no existe)
    @Test
    void shouldReturnNullWhenNotExists() {
        when(appointmentRepository.findById(1L))
                .thenReturn(Optional.empty());

        Appointment result = appointmentService.getAppointmentById(1L);

        assertNull(result);
    }

    // 🔹 SAVE
    @Test
    void shouldSaveAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.save(any(Appointment.class)))
                .thenReturn(appointment);

        Appointment result = appointmentService.saveAppointment(appointment);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // 🔹 DELETE
    @Test
    void shouldDeleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }
}