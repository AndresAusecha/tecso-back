package coop.tecso.examen.repository;

import coop.tecso.examen.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/movimientos-rest-repo", collectionResourceRel = "movimientos-rest-repo")
public interface MovimientoRestRepository extends CrudRepository<Movimiento, UUID> {
}
