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
	
	public String toString() 
	{
       StringBuffer sb = new StringBuffer();
       sb.append("DNI: " + dni);
       sb.append(", nombre: " + nombre);
       sb.append(", apellidos: " + apellidos);
       sb.append(", edad: " + edad);
       sb.append(", email: " + email);
       sb.append(", direccion: " + direccion);

       return sb.toString();
    }
}
