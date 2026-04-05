package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FacturaResponse {
    private String numeroFactura;
    private String idVisita;
    private String nombrePaciente;
    private String nombreCliente;
    private LocalDate fechaVisita;
    private List<DetalleFacturaResponse> detalles;
    private BigDecimal totalServicios;
    private BigDecimal totalMedicamentos;
    private BigDecimal totalCargosAdicionales;
    private BigDecimal totalFactura;

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(String idVisita) {
        this.idVisita = idVisita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public List<DetalleFacturaResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFacturaResponse> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotalServicios() {
        return totalServicios;
    }

    public void setTotalServicios(BigDecimal totalServicios) {
        this.totalServicios = totalServicios;
    }

    public BigDecimal getTotalMedicamentos() {
        return totalMedicamentos;
    }

    public void setTotalMedicamentos(BigDecimal totalMedicamentos) {
        this.totalMedicamentos = totalMedicamentos;
    }

    public BigDecimal getTotalCargosAdicionales() {
        return totalCargosAdicionales;
    }

    public void setTotalCargosAdicionales(BigDecimal totalCargosAdicionales) {
        this.totalCargosAdicionales = totalCargosAdicionales;
    }

    public BigDecimal getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(BigDecimal totalFactura) {
        this.totalFactura = totalFactura;
    }
}
