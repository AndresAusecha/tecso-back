package coop.tecso.examen.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractPersona {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    @NotEmpty
    private String cuit;

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

}
