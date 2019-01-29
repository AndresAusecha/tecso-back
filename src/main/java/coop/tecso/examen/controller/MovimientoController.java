package coop.tecso.examen.controller;

import coop.tecso.examen.requestBodies.NuevoMovimientoRequestBody;
import coop.tecso.examen.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity guardar(@RequestBody NuevoMovimientoRequestBody body) {
        return service.guardar(body);
    }

}

