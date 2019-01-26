package coop.tecso.examen.repository;

import coop.tecso.examen.model.CuentaCorriente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/cuentas",collectionResourceRel = "cuentas")
public interface CuentaRestRepository extends CrudRepository<CuentaCorriente, UUID> {
}
