package coop.tecso.examen.service;

import coop.tecso.examen.requestBodies.ActualizarCuentaRequestBody;
import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CuentaService {
    ResponseEntity<?> guardar(NuevaCuentaRequestBody body);
    ResponseEntity<?> eliminar(UUID id);
    ResponseEntity<?> actualizar(UUID uuid,ActualizarCuentaRequestBody body);
}
