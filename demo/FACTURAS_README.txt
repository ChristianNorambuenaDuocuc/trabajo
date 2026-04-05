Funcionalidad agregada: generación de facturas detalladas por visita

Endpoint:
POST /api/facturas/generar

Ejemplo de request JSON:
{
  "idVisita": "VIS-1001",
  "nombrePaciente": "Max",
  "nombreCliente": "Juan Pérez",
  "fechaVisita": "2026-04-05",
  "serviciosPrestados": [
    {
      "nombre": "Consulta general",
      "descripcion": "Evaluación clínica completa",
      "precio": 25000
    },
    {
      "nombre": "Vacunación",
      "descripcion": "Aplicación vacuna anual",
      "precio": 18000
    }
  ],
  "medicamentosAdministrados": [
    {
      "nombre": "Antibiótico",
      "dosis": "2 ml",
      "costo": 9500
    }
  ],
  "cargosAdicionales": [
    {
      "concepto": "Urgencia",
      "descripcion": "Atención fuera de horario",
      "monto": 12000
    }
  ]
}

Ejemplo de respuesta:
{
  "numeroFactura": "FAC-20260405-00001",
  "idVisita": "VIS-1001",
  "nombrePaciente": "Max",
  "nombreCliente": "Juan Pérez",
  "fechaVisita": "2026-04-05",
  "detalles": [
    {
      "tipo": "SERVICIO",
      "nombre": "Consulta general",
      "descripcion": "Evaluación clínica completa",
      "monto": 25000
    },
    {
      "tipo": "SERVICIO",
      "nombre": "Vacunación",
      "descripcion": "Aplicación vacuna anual",
      "monto": 18000
    },
    {
      "tipo": "MEDICAMENTO",
      "nombre": "Antibiótico",
      "descripcion": "Dosis: 2 ml",
      "monto": 9500
    },
    {
      "tipo": "CARGO_ADICIONAL",
      "nombre": "Urgencia",
      "descripcion": "Atención fuera de horario",
      "monto": 12000
    }
  ],
  "totalServicios": 43000,
  "totalMedicamentos": 9500,
  "totalCargosAdicionales": 12000,
  "totalFactura": 64500
}
