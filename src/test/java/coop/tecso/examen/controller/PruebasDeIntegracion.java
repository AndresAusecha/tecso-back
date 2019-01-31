package coop.tecso.examen.controller;

import coop.tecso.examen.enums.Moneda;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.repository.CuentaRestRepository;
import coop.tecso.examen.repository.PersonaFisicaRestRepository;
import coop.tecso.examen.service.CuentaService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class PruebasDeIntegracion {

    @Autowired
    WebApplicationContext context;

    @Autowired
    PersonaFisicaRestRepository personaFisicaRestRepository;

    @Autowired
    CuentaRestRepository cuentaRestRepository;

    MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    PersonaFisica crearPersonaPOJO() {
        PersonaFisica personaFisica = new PersonaFisica();
        personaFisica.setNombre("Andres");
        personaFisica.setApellido("Ausecha Mosquera");
        personaFisica.setDni((long) 1061759207);
        personaFisica.setCuit("47901247901284903");
        return personaFisicaRestRepository.save(personaFisica);
    }

    CuentaCorriente crearCuentaPOJO(PersonaFisica titularPersonaFisica) {
        return cuentaRestRepository.save(new CuentaCorriente(
                Moneda.PESO,
        5000.00,
        titularPersonaFisica,
        generarNumeroCuenta()
        ));
    }

    private long generarNumeroCuenta() {
        try {
            return cuentaRestRepository.generarMaxNumero();
        } catch (AopInvocationException e) {
            return (long) 1000000000;
        }
    }
}
