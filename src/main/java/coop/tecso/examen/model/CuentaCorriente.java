package coop.tecso.examen.model;

import coop.tecso.examen.enums.Moneda;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cuenta_corriente")
public class CuentaCorriente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false, name = "numero")
    private long numero;

    @NotNull
    private Moneda moneda;

    @NotNull
    private double saldo;

    @OneToMany
    @JoinColumn(name = "movimientos")
    private List<Movimiento> movimientos;

    @ManyToOne
    private PersonaFisica titularPersonaFisica;

    @ManyToOne
    private PersonaJuridica titularPersonaJuridica;

    public CuentaCorriente() {

    }

    public CuentaCorriente(Moneda moneda, double saldo, PersonaJuridica personaJuridica, long numero) {
        this.moneda = moneda;
        this.saldo = saldo;
        this.titularPersonaJuridica = personaJuridica;
        this.numero = numero;
    }

    public CuentaCorriente(Moneda moneda, double saldo, PersonaFisica titularPersonaFisica, long numero) {
        this.moneda = moneda;
        this.saldo = saldo;
        this.titularPersonaFisica = titularPersonaFisica;
        this.numero = numero;
    }

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

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }

    public UUID getId() {
        return id;
    }
}
