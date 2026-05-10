package com.duoc.backend.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factura")
public class FacuraController {

    @PostMapping("/enviar")
    public String enviarFactura(@RequestParam Long id) {
        return "Factura " + id + " enviada por correo";
    }

    @GetMapping("/imprimir")
    public String imprimirFactura(@RequestParam Long id) {
        return "Factura " + id + " impresa correctamente";
    }
}