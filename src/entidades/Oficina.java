package entidades;

import java.io.*;
import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "OFICINA")
public class Oficina implements Serializable
{
	@Id @GeneratedValue
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

	@Override
	public String toString() {
		return "Oficina{" +
				"idoficina=" + idoficina +
				", telefono=" + telefono +
				", direccion='" + direccion + '\'' +
				'}';
	}
}
