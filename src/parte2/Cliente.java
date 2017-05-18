package entidades;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity(name = "CLIENTE")
public class Cliente implements Serializable
{
	@Id
	@Column(name = "DNI")
	private String dni;
		
	@Column(name = "NOMBRE")
	private String nombre;
		
	@Column(name = "APELLIDOS")
	private String apellidos;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "EDAD")
	private int edad;

	@Column(name = "DIRECCION")
	private String direccion;


	@ManyToMany(mappedBy = "clientes")
	private Collection<Cuenta_bancaria> cuenta_bancarias;


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


    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                '}';
    }

	public Collection<Cuenta_bancaria> getCuenta_bancarias() {
		return cuenta_bancarias;
	}

	public void setCuenta_bancarias(Collection<Cuenta_bancaria> cuenta_bancarias) {
		this.cuenta_bancarias = cuenta_bancarias;
	}
}
