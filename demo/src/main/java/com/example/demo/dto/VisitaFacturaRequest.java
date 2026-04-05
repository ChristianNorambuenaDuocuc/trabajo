package com.example.demo.dto;

import com.example.demo.model.CargoAdicional;
import com.example.demo.model.MedicamentoAdministrado;
import com.example.demo.model.ServicioPrestado;

import java.time.LocalDate;
import java.util.List;

public class VisitaFacturaRequest {
    private String idVisita;
    private String nombrePaciente;
    private String nombreCliente;
    private LocalDate fechaVisita;
    private List<ServicioPrestado> serviciosPrestados;
    private List<MedicamentoAdministrado> medicamentosAdministrados;
    private List<CargoAdicional> cargosAdicionales;

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

    public List<ServicioPrestado> getServiciosPrestados() {
        return serviciosPrestados;
    }

    public void setServiciosPrestados(List<ServicioPrestado> serviciosPrestados) {
        this.serviciosPrestados = serviciosPrestados;
    }

    public List<MedicamentoAdministrado> getMedicamentosAdministrados() {
        return medicamentosAdministrados;
    }

    public void setMedicamentosAdministrados(List<MedicamentoAdministrado> medicamentosAdministrados) {
        this.medicamentosAdministrados = medicamentosAdministrados;
    }

    public List<CargoAdicional> getCargosAdicionales() {
        return cargosAdicionales;
    }

    public void setCargosAdicionales(List<CargoAdicional> cargosAdicionales) {
        this.cargosAdicionales = cargosAdicionales;
    }
}
