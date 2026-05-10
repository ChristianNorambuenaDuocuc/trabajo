package com.duoc.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.duoc.backend.user.FacuraController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(FacuraController.class)
@AutoConfigureMockMvc(addFilters = false)
class FacuraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaEnviarFacturaCorrectamente() throws Exception {

        mockMvc.perform(post("/api/factura/enviar")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura 1 enviada por correo"));
    }

    @Test
    void deberiaImprimirFacturaCorrectamente() throws Exception {

        mockMvc.perform(get("/api/factura/imprimir")
                .param("id", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura 2 impresa correctamente"));
    }
}