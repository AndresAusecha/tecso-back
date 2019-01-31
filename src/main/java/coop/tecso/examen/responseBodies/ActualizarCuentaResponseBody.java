package coop.tecso.examen.responseBodies;

import coop.tecso.examen.enums.Moneda;

public class ActualizarCuentaResponseBody {
    private Moneda moneda;
    private long saldo;

    public ActualizarCuentaResponseBody(Moneda moneda, long saldo) {
        this.moneda = moneda;
        this.saldo = saldo;
    }

    public ActualizarCuentaResponseBody(){

    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }
}
