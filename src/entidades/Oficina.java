package entidades;

import java.io.*;
import javax.persistence.*;

@Embeddable
public class Oficina implements Serializable
{

	@Id
	@Column(name = "IDOFICINA",nullable = false)
	private int idoficina;

	@Column(name = "TELEFONO", length = 9)
	private int telefono;

	@Column(name = "DIRECCION", length = 50)
	private String direccion;

	public int getIdOficina() {	return idoficina;	}

	public void setIdOficina(int idoficina) { this.idoficina = idoficina; }

	public int getTelefono() {	return telefono;	}

	public void setTelefono(int telefono) { this.telefono = telefono; }

	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("id: " + idoficina);
		sb.append(", telefono: " + telefono);
		sb.append(", direccion: " + direccion);

		return sb.toString();
	}
}
