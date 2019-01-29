package coop.tecso.examen.repository;

import coop.tecso.examen.model.PersonaFisica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/personasFisicas", collectionResourceRel = "personasFisicas")
public interface PersonaFisicaRestRepository extends CrudRepository<PersonaFisica, UUID> {
}
