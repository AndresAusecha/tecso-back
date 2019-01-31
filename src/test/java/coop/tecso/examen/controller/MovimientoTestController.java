package coop.tecso.examen.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.tecso.examen.enums.TipoMovimiento;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;
import coop.tecso.examen.repository.MovimientoRestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
public class MovimientoTestController extends PruebasDeIntegracion {

    private String movimientosRestController = "/movimientos";
    private String movimientosRestRepo = "/movimientos-rest-repo";

    @Autowired
    private MovimientoRestRepository repo;

    @Test
    @Transactional
    @Rollback
    public void crear() {
        CuentaCorriente cuenta = crearCuentaPOJO(crearPersonaPOJO());
        try {
            String body = "{\n" +
                    "\t\"tipoMovimiento\": \"DEBITO\",\n" +
                    "\t\"descripcion\": \"mov\",\n" +
                    "\t\"importe\": \"100.00\",\n" +
                    "\t\"cuentaId\": " + '"' + cuenta.getId().toString() + '"' +
                    "}";
            String response = mvc.perform(post(movimientosRestController)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);
            assertThat(response).contains("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void crearFallaPorImporteSuperiorAPermitido() {
        CuentaCorriente cuenta = crearCuentaPOJO(crearPersonaPOJO());
        try {
            String body = "{\n" +
                    "\t\"tipoMovimiento\": \"DEBITO\",\n" +
                    "\t\"descripcion\": \"mov\",\n" +
                    "\t\"importe\": \"1200.00\",\n" +
                    "\t\"cuentaId\": " + '"' + cuenta.getId().toString() + '"' +
                    "}";
            mvc.perform(post(movimientosRestController)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isForbidden())
                    .andReturn().getResponse().getContentAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void leer() {
        Movimiento mov = crearMovimientoPOJO();
        try {
            String response = mvc.perform(get(movimientosRestRepo + "/" + mov.getId().toString()))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);
            assertThat(response).contains("fecha");
            assertThat(response).contains("tipoMovimiento");
            assertThat(response).contains("descripcion");
            assertThat(response).contains("importe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void leerMovimientosXCuenta() {
        crearMovimientoPOJO();
        try {
            String response = mvc.perform(get(movimientosRestController + "/listaXCuenta"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);

            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = factory.createParser(response);
            JsonNode actualObj = mapper.readTree(parser);
            Iterator iterator = actualObj.elements();
            assertThat(iterator.hasNext()).isTrue();
            JsonNode nodo = (JsonNode)iterator.next();

            assertThat(nodo.has("cuentaId")).isTrue();
            assertThat(nodo.has("cuentaNumero")).isTrue();
            assertThat(nodo.has("movimientos")).isTrue();
            assertThat(nodo.get("movimientos").isArray()).isTrue();
            assertThat(nodo.get("movimientos").elements()).isNotEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Transactional
    @Rollback
    public void actualizar() {
        Movimiento mov = crearMovimientoPOJO();
        try {
            mvc.perform(put(movimientosRestRepo + "/" + mov.getId().toString()))
                    .andExpect(status().isForbidden())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void eliminar() {
        Movimiento mov = crearMovimientoPOJO();
        try {
            mvc.perform(delete(movimientosRestRepo + "/" + mov.getId().toString()))
                    .andExpect(status().isForbidden())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Movimiento crearMovimientoPOJO(){
        CuentaCorriente cuenta = crearCuentaPOJO(crearPersonaPOJO());
        return repo.save(new Movimiento(TipoMovimiento.DEBITO, "Movimiento de prueba",100.00,cuenta));
    }

}
