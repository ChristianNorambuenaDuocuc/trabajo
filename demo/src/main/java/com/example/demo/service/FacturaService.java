package com.example.demo.service;

import com.example.demo.dto.DetalleFacturaResponse;
import com.example.demo.dto.FacturaResponse;
import com.example.demo.dto.VisitaFacturaRequest;
import com.example.demo.model.CargoAdicional;
import com.example.demo.model.MedicamentoAdministrado;
import com.example.demo.model.ServicioPrestado;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FacturaService {

    private static final AtomicLong SECUENCIA = new AtomicLong(1);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FacturaResponse generarFacturaDetalle(VisitaFacturaRequest request) {
        validarRequest(request);

        List<DetalleFacturaResponse> detalles = new ArrayList<>();

        BigDecimal totalServicios = BigDecimal.ZERO;
        BigDecimal totalMedicamentos = BigDecimal.ZERO;
        BigDecimal totalCargosAdicionales = BigDecimal.ZERO;

        if (request.getServiciosPrestados() != null) {
            for (ServicioPrestado servicio : request.getServiciosPrestados()) {
                BigDecimal monto = valorSeguro(servicio.getPrecio());
                detalles.add(new DetalleFacturaResponse(
                        "SERVICIO",
                        textoSeguro(servicio.getNombre(), "Servicio sin nombre"),
                        textoSeguro(servicio.getDescripcion(), "Sin descripción"),
                        monto
                ));
                totalServicios = totalServicios.add(monto);
            }
        }

        if (request.getMedicamentosAdministrados() != null) {
            for (MedicamentoAdministrado medicamento : request.getMedicamentosAdministrados()) {
                BigDecimal monto = valorSeguro(medicamento.getCosto());
                String descripcion = medicamento.getDosis() == null || medicamento.getDosis().isBlank()
                        ? "Medicamento administrado"
                        : "Dosis: " + medicamento.getDosis();

                detalles.add(new DetalleFacturaResponse(
                        "MEDICAMENTO",
                        textoSeguro(medicamento.getNombre(), "Medicamento sin nombre"),
                        descripcion,
                        monto
                ));
                totalMedicamentos = totalMedicamentos.add(monto);
            }
        }

        if (request.getCargosAdicionales() != null) {
            for (CargoAdicional cargo : request.getCargosAdicionales()) {
                BigDecimal monto = valorSeguro(cargo.getMonto());
                detalles.add(new DetalleFacturaResponse(
                        "CARGO_ADICIONAL",
                        textoSeguro(cargo.getConcepto(), "Cargo adicional"),
                        textoSeguro(cargo.getDescripcion(), "Sin descripción"),
                        monto
                ));
                totalCargosAdicionales = totalCargosAdicionales.add(monto);
            }
        }

        FacturaResponse response = new FacturaResponse();
        response.setNumeroFactura(generarNumeroFactura());
        response.setIdVisita(request.getIdVisita());
        response.setNombrePaciente(request.getNombrePaciente());
        response.setNombreCliente(request.getNombreCliente());
        response.setFechaVisita(request.getFechaVisita());
        response.setDetalles(detalles);
        response.setTotalServicios(totalServicios);
        response.setTotalMedicamentos(totalMedicamentos);
        response.setTotalCargosAdicionales(totalCargosAdicionales);
        response.setTotalFactura(totalServicios.add(totalMedicamentos).add(totalCargosAdicionales));

        return response;
    }

    private void validarRequest(VisitaFacturaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La visita no puede ser nula.");
        }
        if (request.getIdVisita() == null || request.getIdVisita().isBlank()) {
            throw new IllegalArgumentException("El id de la visita es obligatorio.");
        }
        if (request.getFechaVisita() == null) {
            throw new IllegalArgumentException("La fecha de la visita es obligatoria.");
        }
    }

    private String generarNumeroFactura() {
        return "FAC-" + LocalDate.now().format(FORMATTER) + "-" + String.format("%05d", SECUENCIA.getAndIncrement());
    }

    private BigDecimal valorSeguro(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO : valor;
    }

    private String textoSeguro(String valor, String valorPorDefecto) {
        return valor == null || valor.isBlank() ? valorPorDefecto : valor;
    }
}
