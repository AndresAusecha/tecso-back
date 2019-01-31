package coop.tecso.examen.requestBodies;

import coop.tecso.examen.enums.Moneda;

public class ActualizarCuentaRequestBody {

    public ActualizarCuentaRequestBody(){

    }

    private Moneda moneda;
    private long saldo;


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
