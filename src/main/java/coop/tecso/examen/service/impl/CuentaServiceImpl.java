package coop.tecso.examen.service.impl;

import coop.tecso.examen.errors.CustomizedError;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.model.PersonaJuridica;
import coop.tecso.examen.repository.CuentaRestRepository;
import coop.tecso.examen.repository.MovimientoRestRepository;
import coop.tecso.examen.repository.PersonaFisicaRestRepository;
import coop.tecso.examen.repository.PersonaJuridicaRestRepository;
import coop.tecso.examen.requestBodies.ActualizarCuentaRequestBody;
import coop.tecso.examen.responseBodies.ActualizarCuentaResponseBody;
import coop.tecso.examen.requestBodies.NuevaCuentaRequestBody;
import coop.tecso.examen.responseBodies.NuevaCuentaResponseBody;
import coop.tecso.examen.service.CuentaService;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRestRepository repo;

    @Autowired
    private PersonaFisicaRestRepository personaFisicaRestRepository;

    @Autowired
    private PersonaJuridicaRestRepository personaJuridicaRestRepository;

    @Autowired
    MovimientoRestRepository movimientoRestRepository;
    @Override
    public ResponseEntity guardar(NuevaCuentaRequestBody body) {
        UUID idPersonaFisica = body.getTitularPersonaFisicaId();
        UUID idPersonaJuridica = body.getTitularPersonaJuridicaId();
        CuentaCorriente cuenta;

        if (idPersonaFisica != null) {
            Optional<PersonaFisica> res = personaFisicaRestRepository.findById(idPersonaFisica);
            if(!res.isPresent()){
              return new ResponseEntity<>(
                    new CustomizedError("Persona no hallada"),
                    HttpStatus.FORBIDDEN
                );
            }
            cuenta = repo.save(new CuentaCorriente(
                    body.getMoneda(),
                    body.getSaldo(),
                    res.get(),
                    generarNumeroCuenta()
            ));
        } else if (idPersonaJuridica != null) {
            Optional<PersonaJuridica> res = personaJuridicaRestRepository.findById(idPersonaJuridica);
            if(!res.isPresent()){
                return new ResponseEntity<>(
                        new CustomizedError("Persona no hallada"),
                        HttpStatus.FORBIDDEN
                );
            }
            cuenta = repo.save(new CuentaCorriente(
                    body.getMoneda(),
                    body.getSaldo(),
                    res.get(),
                    generarNumeroCuenta()
            ));
        } else {
            return new ResponseEntity<>(new CustomizedError("Titular nulo"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new NuevaCuentaResponseBody(cuenta.getId(), cuenta.getNumero()), HttpStatus.CREATED);
    }

    public ResponseEntity eliminar(UUID idCuenta) {
        Optional<CuentaCorriente> resul = repo.findById(idCuenta);
        if (!resul.isPresent())
            return new ResponseEntity<>(new CustomizedError("Cuenta no encontrada"), HttpStatus.NOT_FOUND);
        CuentaCorriente cuenta = resul.get();
        if (!movimientoRestRepository.findMovimientoByCuenta(cuenta).isEmpty())
            return new ResponseEntity<>(
                    new CustomizedError("La Cuenta tiene movimientos asociados"),
                    HttpStatus.FORBIDDEN
            );
        repo.delete(cuenta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> actualizar(UUID uuid,ActualizarCuentaRequestBody body) {
        Optional<CuentaCorriente> resul = repo.findById(uuid);
        if(!resul.isPresent()){
            return new ResponseEntity<>(
                    new CustomizedError("Cuenta no encontrada"),
                    HttpStatus.FORBIDDEN
            );
        }
        CuentaCorriente cuenta = resul.get();
        if(body.getSaldo() < 0){
            return new ResponseEntity<>(
                    new CustomizedError("Saldo incorrecto"),
                    HttpStatus.FORBIDDEN
            );
        }
        cuenta.setSaldo(body.getSaldo());
        cuenta.setMoneda(body.getMoneda());
        repo.save(cuenta);

        return new ResponseEntity<>(new ActualizarCuentaResponseBody(
                body.getMoneda(),body.getSaldo()),HttpStatus.OK
        );
    }

    private long generarNumeroCuenta() {
        try {
            return repo.generarMaxNumero();
        } catch (AopInvocationException e) {
            return (long) 1000000000;
        }
    }
}
