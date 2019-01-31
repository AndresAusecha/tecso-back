package coop.tecso.examen.controller;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.repository.CuentaRestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
public class CuentasTestController extends PruebasDeIntegracion {

    @Autowired
    CuentaRestRepository repo;

    String repoRestUrl = "/cuentas-rest-repo";
    String controllerRestUrl = "/cuentas";

    @Test
    @Transactional
    @Rollback
    public void crear(){
        PersonaFisica personaFisica = crearPersonaPOJO();
        try {
            String body = "{\"moneda\":\"PESO\", \"saldo\":\"5000.00\",\"titularPersonaFisicaId\":" + "\""
                    + personaFisica.getId().toString() + "\"}";
            System.out.print(body);
            String response = mvc.perform(post(controllerRestUrl)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("id");
            assertThat(response).contains("numero");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void leer() {
        CuentaCorriente cuenta = crearCuentaPOJO(crearPersonaPOJO());
        try {
            String response = mvc.perform(get(repoRestUrl))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);
            assertThat(response).contains(cuenta.getMoneda().name());
            assertThat(response).contains(String.valueOf(cuenta.getNumero()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void actualizar(){
        CuentaCorriente cuentaCorriente = crearCuentaPOJO(crearPersonaPOJO());
        try {
            String body = "{\"moneda\":\"EURO\", \"saldo\":\"4000.00\"}";
            System.out.print(body);
            String response = mvc.perform(put(repoRestUrl + "/" +  cuentaCorriente.getId().toString())
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("EURO");
            assertThat(response).contains("4000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void eliminar() {
        CuentaCorriente cuentaCorriente = crearCuentaPOJO(crearPersonaPOJO());
        try {
            mvc.perform(delete(repoRestUrl + "/" + cuentaCorriente.getId().toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
