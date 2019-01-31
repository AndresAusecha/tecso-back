package coop.tecso.examen.controller;

import coop.tecso.examen.requestBodies.ActualizarCuentaRequestBody;
import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import coop.tecso.examen.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RepositoryRestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity guardar(@RequestBody NuevaCuentaRequestBody body) {
        return service.guardar(body);
    }

    @PutMapping(produces = "application/json",path = "/{uuid}")
    public ResponseEntity actualizar(
            @PathVariable(value = "uuid") UUID uuid,
            @RequestBody ActualizarCuentaRequestBody body) {
        return service.actualizar(uuid,body);
    }

    @DeleteMapping(produces = "application/json",path = "/{uuid}")
    public ResponseEntity eliminar(@PathVariable(value = "uuid") UUID uuid) {
        return service.eliminar(uuid);
    }
}
