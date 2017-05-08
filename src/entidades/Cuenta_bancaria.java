package entidades;

import javax.persistence.*;
import java.io.*;
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
    private double saldo;

    @ManyToMany(mappedBy = "cuentas",cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    public String getIban() { return iban; }

    public void setIban(String iban) {this.iban = iban; }

    public java.sql.Date getFecha() {     return fecha;   }

    public void setFecha(Date fecha) {  this.fecha = fecha; }

    public double getSaldo() {   return saldo;  }

    public void setSaldo(double saldo) {   this.saldo = saldo; }


    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @PrePersist
    @PreUpdate
    public void setDate() {
        this.fecha = new java.sql.Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Cuenta_bancaria{" +
                "iban='" + iban + '\'' +
                ", fecha=" + fecha +
                ", saldo=" + saldo +
                '}';
    }
}