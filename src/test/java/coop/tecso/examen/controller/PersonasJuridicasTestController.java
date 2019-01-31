package coop.tecso.examen.controller;

import static org.assertj.core.api.Assertions.*;

import coop.tecso.examen.model.PersonaJuridica;
import coop.tecso.examen.repository.PersonaJuridicaRestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
public class PersonasJuridicasTestController extends PruebasDeIntegracion {

    @Autowired
    private PersonaJuridicaRestRepository personaJuridicaRestRepository;

    private String personasJuridicasUrl = "/personasJuridicas";

    @Test
    @Transactional
    @Rollback
    public void crear() {
        try {
            String response = mvc.perform(post(personasJuridicasUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "\t\"cuit\": \"3211234312\",\n" +
                            "\t\"razonSocial\": \"Una super empresa\",\n" +
                            "\t\"anioFundacion\": \"1999\"\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("cuit");
            assertThat(response).contains("razonSocial");
            assertThat(response).contains("anioFundacion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void leer() {
        crearPersonaJuridica();
        try {
            String response = mvc.perform(get(personasJuridicasUrl))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            System.out.print(response);
            assertThat(response).contains("cuit");
            assertThat(response).contains("razonSocial");
            assertThat(response).contains("anioFundacion");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void actualizar() {
        PersonaJuridica personaPersistida = crearPersonaJuridica();
        try {
            String response = mvc.perform(put(personasJuridicasUrl + "/" + personaPersistida.getId().toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "\t\"cuit\": \"1000000000\",\n" +
                            "\t\"razonSocial\": \"Una super compania\",\n" +
                            "\t\"anioFundacion\": \"2000\"\n" +
                            "}"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertThat(response).contains("1000000000");
            assertThat(response).contains("Una super compania");
            assertThat(response).contains("2000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void eliminar() {
        PersonaJuridica personaPersistida = crearPersonaJuridica();
        try {
            mvc.perform(delete(personasJuridicasUrl + "/" + personaPersistida.getId().toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PersonaJuridica crearPersonaJuridica(){
        PersonaJuridica personaJuridica = new PersonaJuridica();
        personaJuridica.setAnioFundacion("1999");
        personaJuridica.setRazonSocial("Una super empresa");
        personaJuridica.setCuit("3211234312");
        return personaJuridicaRestRepository.save(personaJuridica);
    }

}
