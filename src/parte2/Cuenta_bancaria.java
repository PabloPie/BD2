package entidades;

import javax.persistence.*;
import java.io.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "CUENTA_BANCARIA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cuenta_bancaria implements Serializable
{
    @Id
    @Column(name = "IBAN")
    private String iban;

    @Column(name = "FECHA")
    private java.sql.Date fecha;

    @Column(name = "SALDO")
    private double saldo;


    @JoinTable(name="CLIENTES_CUENTAS",
            joinColumns = {
                    @JoinColumn(name = "iban", referencedColumnName = "iban")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "dni", referencedColumnName = "dni")
            }
    )
    @ManyToMany
    private Collection<Cliente> clientes;



    public String getIban() { return iban; }

    public void setIban(String iban) {this.iban = iban; }

    public java.sql.Date getFecha() {     return fecha;   }

    public void setFecha(Date fecha) {  this.fecha = fecha; }

    public double getSaldo() {   return saldo;  }

    public void setSaldo(double saldo) {   this.saldo = saldo; }

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

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }
}