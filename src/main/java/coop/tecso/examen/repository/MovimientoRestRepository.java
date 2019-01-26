package coop.tecso.examen.repository;

import coop.tecso.examen.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/movimientos",collectionResourceRel = "movimientos")
public interface MovimientoRestRepository extends CrudRepository<Movimiento, UUID> {
}
