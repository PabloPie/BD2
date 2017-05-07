package entidades;

import javax.persistence.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Entity(name = "CUENTA_CORRIENTE")
@DiscriminatorValue(value = "Cuenta_corriente")

public abstract class Cuenta_corriente extends Cuenta_bancaria implements Serializable
{
    @Column(name = "IDOFICINA")
    private double idoficina;

    public double getIdoficina() {  return idoficina;  }

    public void setIdoficina(double idoficina) {  this.idoficina = idoficina;  }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("IBAN: " + getIban());
        sb.append(", tipo_cuenta: Cuenta_corriente");
        sb.append(", fecha: " + getFecha());
        sb.append(", saldo: " + getSaldo());
        sb.append(", idoficina" + idoficina);

        return sb.toString();
    }
}