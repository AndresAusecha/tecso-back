package coop.tecso.examen.dto;

import coop.tecso.examen.enums.TipoMovimiento;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovimientoDto {
    private UUID id;
    private LocalDateTime fecha;
    private TipoMovimiento tipoMovimiento;
    private String descripcion;
    private double importe;

    public MovimientoDto(UUID id, LocalDateTime fecha, TipoMovimiento tipoMovimiento, String descripcion, double importe) {
        this.id = id;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }
}
