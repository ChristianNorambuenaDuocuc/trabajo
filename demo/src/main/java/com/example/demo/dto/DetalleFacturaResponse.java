package com.example.demo.dto;

import java.math.BigDecimal;

public class DetalleFacturaResponse {
    private String tipo;
    private String nombre;
    private String descripcion;
    private BigDecimal monto;

    public DetalleFacturaResponse() {
    }

    public DetalleFacturaResponse(String tipo, String nombre, String descripcion, BigDecimal monto) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
