package coop.tecso.examen.controller;

import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import coop.tecso.examen.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity guardar(@RequestBody NuevaCuentaRequestBody body) {
        return service.guardar(body);
    }
}
