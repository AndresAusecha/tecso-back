package coop.tecso.examen.model;

import coop.tecso.examen.enums.TipoMovimiento;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Movimiento {
    @Id @GeneratedValue
    private UUID idMovimiento;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private TipoMovimiento tipoMovimiento;

    @NotEmpty @Size(max = 200)
    private String descripcion;

    @NotNull
    private float importe;

    @ManyToOne @NotEmpty
    private CuentaCorriente cuenta;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

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

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }
}
