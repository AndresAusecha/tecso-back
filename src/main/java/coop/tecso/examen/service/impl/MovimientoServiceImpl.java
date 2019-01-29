package coop.tecso.examen.service.impl;

import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.dto.MovimientosXCuentaDto;
import coop.tecso.examen.enums.Moneda;
import coop.tecso.examen.errors.CustomizedError;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;
import coop.tecso.examen.repository.CuentaRestRepository;
import coop.tecso.examen.repository.MovimientoRestRepository;
import coop.tecso.examen.requestBodies.NuevoMovimientoRequestBody;
import coop.tecso.examen.responseBodies.NuevoMovimientoResponseBody;
import coop.tecso.examen.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRestRepository repo;

    @Autowired
    CuentaRestRepository cuentaRestRepository;

    @Override
    public ResponseEntity guardar(NuevoMovimientoRequestBody body) {
        double importe = body.getImporte();
        CuentaCorriente cuentaCorriente = cuentaRestRepository.findById(body.getCuentaId()).get();
        double saldo = cuentaCorriente.getSaldo();

        if (cuentaCorriente.getMoneda() == Moneda.PESO && importe > 1000) {
            return new ResponseEntity<>(new CustomizedError("Saldo no permitido"), HttpStatus.FORBIDDEN);
        }

        if (cuentaCorriente.getMoneda() == Moneda.DOLAR && importe > 300) {
            return new ResponseEntity<>(new CustomizedError("Saldo no permitido"), HttpStatus.FORBIDDEN);
        }

        if (cuentaCorriente.getMoneda() == Moneda.EURO && importe > 150) {
            return new ResponseEntity<>(new CustomizedError("Saldo no permitido"), HttpStatus.FORBIDDEN);
        }

        if (saldo > importe) {
            cuentaCorriente.setSaldo(saldo - importe);
            Movimiento mov = repo.save(
                    new Movimiento(body.getTipoMovimiento(),
                            body.getDescripcion(),
                            body.getImporte(),
                            cuentaCorriente));
            return new ResponseEntity<>(new NuevoMovimientoResponseBody(mov.getId()), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Error("Importe sobrepasa saldo"), HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<?> listaMovimientosXCuenta() {
        Iterator cuentas = cuentaRestRepository.findAll().iterator();
        List<MovimientosXCuentaDto> movimientosXCuentaDtos = new ArrayList<>();
        List<Movimiento> movimientos;
        CuentaCorriente cuenta;

        while(cuentas.hasNext()){
            cuenta = (CuentaCorriente) cuentas.next();
            movimientos = repo.findMovimientoByCuentaOrderByFechaDesc(cuenta);
            MovimientosXCuentaDto movimientosXCuentaDto = new MovimientosXCuentaDto();
            movimientosXCuentaDto.setCuentaId(cuenta.getId());
            movimientosXCuentaDto.setCuentaNumero(cuenta.getNumero());
            Iterator movimientosIterator = movimientos.iterator();
            List<MovimientoDto> movimientoDtos = new ArrayList<>();
            Movimiento movimiento;

            while(movimientosIterator.hasNext()){
                movimiento = (Movimiento) movimientosIterator.next();
                movimientoDtos.add(new MovimientoDto(
                        movimiento.getId(),
                        movimiento.getFecha(),
                        movimiento.getTipoMovimiento(),
                        movimiento.getDescripcion(),
                        movimiento.getImporte()));
            }
            movimientosXCuentaDto.setMovimientos(movimientoDtos);
            movimientosXCuentaDtos.add(movimientosXCuentaDto);
        }
        return new ResponseEntity<>(movimientosXCuentaDtos, HttpStatus.OK);
    }
}
