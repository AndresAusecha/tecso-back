package coop.tecso.examen.service;

import coop.tecso.examen.requestBodies.NuevoMovimientoRequestBody;
import org.springframework.http.ResponseEntity;

public interface MovimientoService {
    ResponseEntity<?> guardar(NuevoMovimientoRequestBody body);
    ResponseEntity<?> listaMovimientosXCuenta();
}
