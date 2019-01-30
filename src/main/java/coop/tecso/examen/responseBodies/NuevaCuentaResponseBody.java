package coop.tecso.examen.responseBodies;

import java.io.Serializable;
import java.util.UUID;

public class NuevaCuentaResponseBody implements Serializable {
    private UUID id;
    private long numero;

    public NuevaCuentaResponseBody(UUID id, long numero) {
        this.id = id;
        this.numero = numero;
    }

    public UUID getId() {
        return id;
    }

    public long getNumero() {
        return numero;
    }
}
