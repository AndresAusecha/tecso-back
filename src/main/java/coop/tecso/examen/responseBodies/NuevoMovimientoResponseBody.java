package coop.tecso.examen.responseBodies;

import java.util.UUID;

public class NuevoMovimientoResponseBody {
    private UUID id;

    public NuevoMovimientoResponseBody(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
