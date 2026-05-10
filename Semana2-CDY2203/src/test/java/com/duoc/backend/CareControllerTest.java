package com.duoc.backend;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.List;
import java.util.Optional;

import com.duoc.backend.care.CareController;
import com.duoc.backend.care.CareRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import com.duoc.backend.care.Care;

@WebMvcTest(CareController.class)
@WithMockUser
class CareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CareRepository careRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔹 GET ALL
    @Test
    void shouldReturnAllCares() throws Exception {
        Care care = new Care();
        care.setId(1L);

        when(careRepository.findAll()).thenReturn(List.of(care));

        mockMvc.perform(get("/care"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    // 🔹 GET BY ID
    @Test
    void shouldReturnCareById() throws Exception {
        Care care = new Care();
        care.setId(1L);

        when(careRepository.findById(1L))
                .thenReturn(Optional.of(care));

        mockMvc.perform(get("/care/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 🔹 GET BY ID (no existe)
    @Test
    void shouldReturnNullWhenCareNotFound() throws Exception {
        when(careRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/care/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    // 🔹 POST
    @Test
    void shouldSaveCare() throws Exception {
        Care care = new Care();
        care.setId(1L);

        when(careRepository.save(any(Care.class)))
                .thenReturn(care);

        mockMvc.perform(post("/care")
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(care)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 🔹 DELETE
    @Test
    void shouldDeleteCare() throws Exception {
        doNothing().when(careRepository).deleteById(1L);

        mockMvc.perform(delete("/care/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}