package entidades;

import javax.persistence.*;
import java.io.*;


@Entity(name = "TRANSFERENCIA")

public class Transferencia extends Operacion implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "iban_receptora", referencedColumnName = "iban")
    private Cuenta_bancaria iban_receptora;

    public Cuenta_bancaria getIban_receptora() {
        return iban_receptora;
    }

    public void setIban_receptora(Cuenta_bancaria iban_receptora) {
        this.iban_receptora = iban_receptora;
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "idoperacion=" + getIdoperacion() +
                ", fecha_hora=" + getFecha_hora() +
                ", cantidad=" + getCantidad() +
                ", descripcion='" + getDescripcion() + '\'' +
                ", iban_origen=" + getIban_origen() +
                ", iban_receptora=" + iban_receptora +
                '}';
    }
}