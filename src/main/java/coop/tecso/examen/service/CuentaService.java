package coop.tecso.examen.service;

import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import org.springframework.http.ResponseEntity;

public interface CuentaService {
    ResponseEntity<?> guardar(NuevaCuentaRequestBody body);
}
