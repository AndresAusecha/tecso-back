package coop.tecso.examen.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PersonaJuridica extends AbstractPersona {

    @NotNull
    @Size(max = 100)
    private String razonSocial;

    @NotNull
    @Size(max = 4)
    private String anioFundacion;

    @OneToMany
    @JoinColumn(name = "cuentas")
    List<CuentaCorriente> cuentas = new ArrayList<>();

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(String anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public List<CuentaCorriente> getCuentas() {
        return cuentas;
    }

    public void setCuentas(CuentaCorriente cuenta) {
        this.cuentas.add(cuenta);
    }
    public UUID getId() {
        return id;
    }
}
