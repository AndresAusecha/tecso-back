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

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @NotNull
    private TipoMovimiento tipoMovimiento;

    @NotEmpty
    @Size(max = 200)
    private String descripcion;

    @NotNull
    private double importe;

    @ManyToOne
    private CuentaCorriente cuenta;

    public Movimiento(){

    }

    public Movimiento(TipoMovimiento tipoMovimiento, String descripcion, double importe, CuentaCorriente cuenta) {
        this.tipoMovimiento = tipoMovimiento;
        this.descripcion = descripcion;
        this.importe = importe;
        this.cuenta = cuenta;
    }

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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    public UUID getId() {
        return id;
    }
}
