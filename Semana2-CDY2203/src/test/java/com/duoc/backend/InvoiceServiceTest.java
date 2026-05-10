package com.duoc.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.backend.Invoice.Invoice;
import com.duoc.backend.Invoice.InvoiceRepository;
import com.duoc.backend.Invoice.InvoiceService;
import com.duoc.backend.care.Care;
import com.duoc.backend.care.CareRepository;
import com.duoc.backend.medication.Medication;
import com.duoc.backend.medication.MedicationRepository;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private InvoiceService invoiceService;

@Test
void shouldSaveInvoiceCorrectly() {

    Medication med = new Medication();
    med.setId(1L);
    med.setCost(500.0);

    Care care = new Care();
    care.setId(1L);
    care.setCost(1000.0);

    Invoice invoice = new Invoice();
    invoice.setMedications(List.of(med));
    invoice.setCares(List.of(care));

    when(medicationRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(med));

    when(careRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(care));

    when(invoiceRepository.save(any(Invoice.class)))
            .thenAnswer(i -> i.getArgument(0));

    Invoice result = invoiceService.saveInvoice(invoice);

    assertEquals(1500, result.getTotalCost());
}

@Test
void shouldThrowExceptionWhenMedicationNotExists() {

    Medication med = new Medication();
    med.setId(1L);

    Invoice invoice = new Invoice();
    invoice.setMedications(List.of(med));
    invoice.setCares(List.of());

    when(medicationRepository.findAllById(List.of(1L)))
            .thenReturn(List.of()); // no existe

    assertThrows(IllegalArgumentException.class, () -> {
        invoiceService.saveInvoice(invoice);
    });
}

@Test
void shouldThrowExceptionWhenCareNotExists() {

    Care care = new Care();
    care.setId(1L);

    Invoice invoice = new Invoice();
    invoice.setMedications(List.of());
    invoice.setCares(List.of(care));

    when(medicationRepository.findAllById(List.of()))
            .thenReturn(List.of());

    when(careRepository.findAllById(List.of(1L)))
            .thenReturn(List.of()); // no existe

    assertThrows(IllegalArgumentException.class, () -> {
        invoiceService.saveInvoice(invoice);
    });
}

@Test
void shouldDeleteInvoice() {
    invoiceService.deleteInvoice(1L);

    verify(invoiceRepository).deleteById(1L);
}
@Test
void shouldReturnInvoiceWhenExists() {
    Invoice invoice = new Invoice();

    when(invoiceRepository.findById(1L))
            .thenReturn(Optional.of(invoice));

    Invoice result = invoiceService.getInvoiceById(1L);

    assertNotNull(result);
}

}