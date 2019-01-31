package coop.tecso.examen.service;

import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CuentaService {
    ResponseEntity<?> guardar(NuevaCuentaRequestBody body);
    ResponseEntity<?> eliminar(UUID id);
}
