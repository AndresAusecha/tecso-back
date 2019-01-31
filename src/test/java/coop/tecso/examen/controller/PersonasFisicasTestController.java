package coop.tecso.examen.controller;

import static org.assertj.core.api.Assertions.*;

import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.repository.PersonaFisicaRestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
public class PersonasFisicasTestController extends PruebasDeIntegracion {

    @Autowired
    private PersonaFisicaRestRepository personaFisicaRestRepository;

    private String personasFisicasUrl = "/personasFisicas";

    @Test
    @Transactional
    @Rollback
    public void crear() {
        try {
            String response = mvc.perform(post(personasFisicasUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "\t\"nombre\": \"Andres\",\n" +
                            "\t\"apellido\":\"Ausecha Mosquera\",\n" +
                            "\t\"dni\":\"1061759207\",\n" +
                            "\t\"cuit\":\"47901247901284903\"\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("cuit");
            assertThat(response).contains("nombre");
            assertThat(response).contains("apellido");
            assertThat(response).contains("dni");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void leer() {
        crearPersonaPOJO();
        try {
            String response = mvc.perform(get(personasFisicasUrl))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);
            assertThat(response).contains("cuit");
            assertThat(response).contains("nombre");
            assertThat(response).contains("apellido");
            assertThat(response).contains("dni");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void actualizar() {
        PersonaFisica personaPersistida = crearPersonaPOJO();
        try {
            String response = mvc.perform(put(personasFisicasUrl + "/" + personaPersistida.getId().toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "\t\"nombre\": \"Dumar Andres\",\n" +
                            "\t\"apellido\":\"Ausecha Mosquera\",\n" +
                            "\t\"dni\":\"1017181249\",\n" +
                            "\t\"cuit\":\"1000000000\"\n" +
                            "}"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("Dumar Andres");
            assertThat(response).contains("1017181249");
            assertThat(response).contains("1000000000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void eliminar() {
        PersonaFisica personaPersistida = crearPersonaPOJO();
        try {
            mvc.perform(delete(personasFisicasUrl + "/" + personaPersistida.getId().toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
