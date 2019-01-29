package coop.tecso.examen.dto;

import java.util.UUID;
import java.util.List;

public class MovimientosXCuentaDto {
    private UUID cuentaId;
    private long cuentaNumero;
    private List<MovimientoDto> movimientos;


    public UUID getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(UUID cuentaId) {
        this.cuentaId = cuentaId;
    }

    public long getCuentaNumero() {
        return cuentaNumero;
    }

    public void setCuentaNumero(long cuentaNumero) {
        this.cuentaNumero = cuentaNumero;
    }

    public List<MovimientoDto> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDto> movimientos) {
        this.movimientos = movimientos;
    }
}
