package coop.tecso.examen.requestBodies;

import coop.tecso.examen.enums.Moneda;

import java.util.UUID;

public class NuevaCuentaRequestBody {
    private Moneda moneda;
    private double saldo;
    private UUID titularPersonaFisicaId;
    private UUID titularPersonaJuridicaId;

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

    public UUID getTitularPersonaFisicaId() {
        return titularPersonaFisicaId;
    }

    public void setTitularPersonaFisicaId(UUID titularPersonaFisicaId) {
        this.titularPersonaFisicaId = titularPersonaFisicaId;
    }

    public UUID getTitularPersonaJuridicaId() {
        return titularPersonaJuridicaId;
    }

    public void setTitularPersonaJuridicaId(UUID titularPersonaJuridicaId) {
        this.titularPersonaJuridicaId = titularPersonaJuridicaId;
    }
}
