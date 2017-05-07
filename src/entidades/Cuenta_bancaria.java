package entidades;

import javax.persistence.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "CUENTAS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_CUENTA")

public abstract class Cuenta_bancaria implements Serializable
{
    @Id
    @Column(name = "IBAN", nullable = false, length = 34)
    private String iban;

    @Column(name = "FECHA", nullable = false,length = 50)
    private java.sql.Date fecha;

    @Column(name = "SALDO")
    private int saldo;

    public String getIban() { return iban; }

    public void setIban(String iban) {this.iban = iban; }

    public java.sql.Date getFecha() {     return fecha;   }

    public void setFecha(Date fecha) {  this.fecha = fecha; }

    public int getSaldo() {   return saldo;  }

    public void setSaldo(int saldo) {   this.saldo = saldo; }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("IBAN: " + iban);
        sb.append(", fecha: " + fecha);
        sb.append(", saldo: " + saldo);

        return sb.toString();
    }
}