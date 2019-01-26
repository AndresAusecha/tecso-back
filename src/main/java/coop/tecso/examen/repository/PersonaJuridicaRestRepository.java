package coop.tecso.examen.repository;

import coop.tecso.examen.model.PersonaJuridica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/personasJuridicas",collectionResourceRel = "personasJuridicas")
public interface PersonaJuridicaRestRepository extends CrudRepository<PersonaJuridica, Long> { }
