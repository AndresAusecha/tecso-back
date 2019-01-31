package coop.tecso.examen.repository;

import coop.tecso.examen.model.CuentaCorriente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/cuentas-rest-repo", collectionResourceRel = "cuentas-rest-repo")
public interface CuentaRestRepository extends CrudRepository<CuentaCorriente, UUID> {

    @Query("SELECT MAX(C.numero) + 1 FROM CuentaCorriente C")
    int generarMaxNumero();


}
