package entidades;

import javax.persistence.*;
import java.io.*;
import java.sql.Date;

@Entity(name = "OPERACIONES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_OPERACION")

public abstract class Operacion implements Serializable
{
    @Id @GeneratedValue
    @Column(name = "IDOPERACION", nullable = false)
    private int idoperacion;

    @Column(name = "FECHA_HORA", nullable = false,length = 50)
    private java.sql.Date fecha_hora;

    @Column(name = "CANTIDAD")
    private double cantidad;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    private Cuenta_bancaria iban_origen;



    public int getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(int idoperacion) {
        this.idoperacion = idoperacion;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta_bancaria getIban_origen() {
        return iban_origen;
    }

    public void setIban_origen(Cuenta_bancaria iban_origen) {
        this.iban_origen = iban_origen;
    }

    @PrePersist
    @PreUpdate
    public void setDate() {
        this.fecha_hora = new java.sql.Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Operacion{" +
                "idoperacion=" + idoperacion +
                ", fecha_hora=" + fecha_hora +
                ", cantidad=" + cantidad +
                ", descripcion='" + descripcion + '\'' +
                ", iban_origen=" + iban_origen.getIban() +
                '}';
    }
}