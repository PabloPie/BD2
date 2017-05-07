package entidades;

import javax.persistence.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Entity(name = "CUENTA_AHORRO")
@DiscriminatorValue(value = "Cuenta_ahorro")

public abstract class Cuenta_ahorro extends Cuenta_bancaria implements Serializable
{
    @Column(name = "TIPO_INTERES")
    private double tipo_interes;

    public double getTipo_interes() {  return tipo_interes;  }

    public void setTipo_interes(double tipo_interes) { this.tipo_interes = tipo_interes;  }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("IBAN: " + getIban());
        sb.append(", tipo_cuenta: Cuenta_ahorro");
        sb.append(", fecha: " + getFecha());
        sb.append(", saldo: " + getSaldo());
        sb.append(", tipo_interes" + tipo_interes);

        return sb.toString();
    }
}