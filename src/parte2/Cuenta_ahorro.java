package entidades;

import javax.persistence.*;
import java.io.*;

@Entity(name = "CUENTA_DE_AHORRO")
public class Cuenta_ahorro extends Cuenta_bancaria implements Serializable
{
    @Column(name = "TIPO_INTERES")
    private double tipo_interes;

    public double getTipo_interes() {  return tipo_interes;  }

    public void setTipo_interes(double tipo_interes) { this.tipo_interes = tipo_interes;  }

    @Override
    public String toString() {
        return "Cuenta_bancaria{" +
                "iban='" + getIban() + '\'' +
                ", tipo_cuenta= Cuenta_ahorro" +
                ", fecha=" + getFecha() +
                ", saldo=" + getSaldo() +
                ", tipo_interes=" + tipo_interes +
                ", clientes=" + getClientes() +
                '}';
    }
}