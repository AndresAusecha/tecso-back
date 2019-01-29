package coop.tecso.examen.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.tecso.examen.errors.CustomizedError;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.model.PersonaJuridica;
import coop.tecso.examen.repository.CuentaRestRepository;
import coop.tecso.examen.repository.PersonaFisicaRestRepository;
import coop.tecso.examen.repository.PersonaJuridicaRestRepository;
import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import coop.tecso.examen.responseBodies.NuevaCuentaResponseBody;
import coop.tecso.examen.service.CuentaService;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRestRepository repo;

    @Autowired
    private PersonaFisicaRestRepository personaFisicaRestRepository;

    @Autowired
    private PersonaJuridicaRestRepository personaJuridicaRestRepository;

    @Override
    public ResponseEntity guardar(NuevaCuentaRequestBody body) {
        UUID idPersonaFisica = body.getTitularPersonaFisicaId();
        UUID idPersonaJuridica = body.getTitularPersonaJuridicaId();
        PersonaFisica personaFisica;
        PersonaJuridica personaJuridica;
        CuentaCorriente cuenta;

        if (idPersonaFisica != null) {
            personaFisica = personaFisicaRestRepository.findById(idPersonaFisica).get();
            cuenta = repo.save(new CuentaCorriente(
                    body.getMoneda(),
                    body.getSaldo(),
                    personaFisica,
                    generarNumeroCuenta()
            ));
        } else if (idPersonaJuridica != null) {
            personaJuridica = personaJuridicaRestRepository.findById(idPersonaJuridica).get();
            cuenta = repo.save(new CuentaCorriente(
                    body.getMoneda(),
                    body.getSaldo(),
                    personaJuridica,
                    generarNumeroCuenta()
            ));
        } else {
            return new ResponseEntity<>(new CustomizedError("Titular nulo"),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new NuevaCuentaResponseBody(cuenta.getId(), cuenta.getNumero()), HttpStatus.CREATED);
    }

    private long generarNumeroCuenta() {
        try {
            return repo.generarMaxNumero();
        } catch (AopInvocationException e) {
            return (long) 1000000000;
        }
    }
}
