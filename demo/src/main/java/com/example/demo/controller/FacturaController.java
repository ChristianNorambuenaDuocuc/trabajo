package com.example.demo.controller;

import com.example.demo.dto.FacturaResponse;
import com.example.demo.dto.VisitaFacturaRequest;
import com.example.demo.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/generar")
    public ResponseEntity<FacturaResponse> generarFactura(@RequestBody VisitaFacturaRequest request) {
        FacturaResponse factura = facturaService.generarFacturaDetalle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(factura);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarErrorValidacion(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
    }
}
