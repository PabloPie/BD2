package entidades;

import javax.persistence.*;
import java.io.*;

@Entity(name = "CUENTA_CORRIENTE")
@DiscriminatorValue(value = "Cuenta_corriente")

public class Cuenta_corriente extends Cuenta_bancaria implements Serializable
{
    @ManyToOne
    private Oficina oficina;

    public Oficina getOficina() {  return oficina;  }

    public void setOficina(Oficina oficina) {  this.oficina = oficina;  }

    @Override
    public String toString() {
        return "Cuenta_bancaria{" +
                "iban='" + getIban() + '\'' +
                ", tipo_cuenta= Cuenta_corriente" +
                ", fecha=" + getFecha() +
                ", saldo=" + getSaldo() +
                ", idoficina=" + oficina +
                ", clientes=" + getClientes() +
                '}';
    }
}