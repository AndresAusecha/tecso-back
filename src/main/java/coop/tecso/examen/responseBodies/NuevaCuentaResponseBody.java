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

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public long getNumero() {
        return numero;
    }
}
