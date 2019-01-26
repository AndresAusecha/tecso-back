package coop.tecso.examen.model;

import coop.tecso.examen.enums.Moneda;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
public class CuentaCorriente{
    @Id @GeneratedValue
    private UUID id;

    @Column(unique = true) @GeneratedValue
    @Size(min = 1000000000)
    private Long numero;

    @NotNull
    private Moneda moneda;

    @NotNull
    private double saldo;

    @OneToMany @JoinColumn(name = "movimientos")
    private Set<Movimiento> movimientos;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }
}
