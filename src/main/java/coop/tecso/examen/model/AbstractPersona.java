package coop.tecso.examen.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractPersona {
    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Size(max = 50)
    String cuit;
}
