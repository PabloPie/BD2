package entidades;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity(name = "CLIENTE")
public class Cliente implements Serializable
{
	@Id
	@Column(name = "DNI", nullable = false, length = 9)
	private String dni;
		
	@Column(name = "NOMBRE", nullable = false,length = 50)
	private String nombre;
		
	@Column(name = "APELLIDOS", length = 50)
	private String apellidos;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "EDAD")
	private int edad;

	@Column(name = "DIRECCION", length = 50)
	private String direccion;

	@ManyToMany(cascade = CascadeType.ALL)
    //@JoinTable(name="CLIENTE_CUENTAS", joinColumns = {@JoinColumn(name="DNI", referencedColumnName = "DNI")},
    //inverseJoinColumns = {@JoinColumn(name="IBAN", referencedColumnName = "IBAN")})
	private List<Cuenta_bancaria> cuentas;

	@Version
	@Column(name = "FECHA_ULTIMA_ACTUALIZACION")
	private Date ultimaActualizacion;

	public String getDni()
	{
		return dni;
	}

	public void setDni(String dni)
	{
		this.dni = dni;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}

	public Date getUltimaActualizacion() 
	{
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Date ultimaActualizacion) 
	{
		this.ultimaActualizacion = ultimaActualizacion;
	}

	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public int getEdad()
	{
		return edad;
	}

	public void setEdad(int edad)
	{
		this.edad = edad;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cuenta_bancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta_bancaria> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", Cuentas=" + cuentas +
                ", ultimaActualizacion=" + ultimaActualizacion +
                '}';
    }
}
