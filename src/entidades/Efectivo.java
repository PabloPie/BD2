package entidades;

import javax.persistence.*;
import java.io.*;

@Entity(name = "EFECTIVO")
@DiscriminatorValue(value = "efectivo")

public class Efectivo extends Operacion implements Serializable
{
    @ManyToOne
    private Oficina oficina;

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public String toString() {

        return "Transferencia{" +
                "idoperacion=" + getIdoperacion() +
                ", fecha_hora=" + getFecha_hora() +
                ", cantidad=" + getCantidad() +
                ", descripcion='" + getDescripcion() + '\'' +
                ", iban_origen=" + getIban_origen() +
                ", oficina=" + oficina +
                '}';
    }
}