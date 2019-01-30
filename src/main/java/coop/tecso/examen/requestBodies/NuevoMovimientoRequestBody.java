package coop.tecso.examen.requestBodies;

import coop.tecso.examen.enums.TipoMovimiento;

import java.util.UUID;

public class NuevoMovimientoRequestBody {
    private TipoMovimiento tipoMovimiento;
    private String descripcion;
    private UUID cuentaId;
    private double importe;

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public UUID getCuentaId() {
        return cuentaId;
    }

    public double getImporte() {
        return importe;
    }
}
