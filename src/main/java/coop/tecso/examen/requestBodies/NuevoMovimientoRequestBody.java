package coop.tecso.examen.requestBodies;

import coop.tecso.examen.enums.TipoMovimiento;

import java.io.Serializable;
import java.util.UUID;

public class NuevoMovimientoRequestBody implements Serializable {

    private TipoMovimiento tipoMovimiento;
    private String descripcion;
    private UUID cuentaId;
    private double importe;


    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UUID getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(UUID cuentaId) {
        this.cuentaId = cuentaId;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
