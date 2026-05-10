package com.duoc.backend;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;


import com.duoc.backend.Invoice.InvoiceController;
import com.duoc.backend.Invoice.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



import com.duoc.backend.Invoice.Invoice;

@WebMvcTest(InvoiceController.class)
@WithMockUser
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private ObjectMapper objectMapper;

    // GET ALL
    @Test
    void shouldReturnAllInvoices() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceService.getAllInvoices()).thenReturn(List.of(invoice));

        mockMvc.perform(get("/invoice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    // GET BY ID
    @Test
    void shouldReturnInvoiceById() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceService.getInvoiceById(1L)).thenReturn(invoice);

        mockMvc.perform(get("/invoice/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // POST
    @Test
    void shouldSaveInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceService.saveInvoice(any(Invoice.class))).thenReturn(invoice);

        mockMvc.perform(post("/invoice")
        .with(csrf())   // 
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(invoice)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
    }

    // DELETE
    @Test
    void shouldDeleteInvoice() throws Exception {
        doNothing().when(invoiceService).deleteInvoice(1L);

       mockMvc.perform(delete("/invoice/1")
        .with(csrf()))   // 
        .andExpect(status().isOk());
    }

    // PDF OK
    @Test
    void shouldReturnPdfWhenInvoiceExists() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setPatientName("Firulais");
        invoice.setDate(LocalDate.of(2024, 1, 1));
        invoice.setTotalCost(1000.0);
        invoice.setCares(List.of());
        invoice.setMedications(List.of());

        when(invoiceService.getInvoiceById(1L)).thenReturn(invoice);

        mockMvc.perform(get("/invoice/pdf/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/pdf"));
    }

    // PDF NOT FOUND
    @Test
    void shouldReturn404WhenInvoiceNotFound() throws Exception {
        when(invoiceService.getInvoiceById(1L)).thenReturn(null);

        mockMvc.perform(get("/invoice/pdf/1"))
                .andExpect(status().isNotFound());
    }

    // PDF ERROR
    @Test
    void shouldReturn500WhenErrorOccurs() throws Exception {
        when(invoiceService.getInvoiceById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/invoice/pdf/1"))
                .andExpect(status().isInternalServerError());
    }
}