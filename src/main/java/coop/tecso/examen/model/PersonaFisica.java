package coop.tecso.examen.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PersonaFisica extends AbstractPersona {
    @NotEmpty
    @NotBlank
    @Size(max = 80)
    private String nombre;

    @NotEmpty
    @NotBlank
    @Size(max = 250)
    private String apellido;

    @NotNull
    private Long dni;

    public String getNombre() {
        return nombre;
    }

    @OneToMany
    @JoinColumn(name = "cuentas")
    List<CuentaCorriente> cuentas = new ArrayList<>();

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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
