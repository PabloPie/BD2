package test;

import javax.persistence.*;
import entidades.*;
import javafx.beans.binding.ListBinding;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestCliente 
{
	public static void main(String[] args) 
	{
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("Banquito");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();

		Oficina of = new Oficina();
		of.setDireccion("Calle de la oficina 12, esc 4ª");
		of.setTelefono(976543212);

        em.persist(of);

		Cuenta_ahorro ca = new Cuenta_ahorro();
		ca.setTipo_interes(6.45);
        ca.setSaldo(200.9);
        ca.setIban("123456781234567812345678123456789A");

        em.persist(ca);

		Cuenta_corriente cc = new Cuenta_corriente();
		cc.setOficina(of);
		cc.setSaldo(100.70);
		cc.setIban("987654321987654321987654321987654A");

        em.persist(cc);

        Cliente cliente = new Cliente();
        cliente.setNombre("Paolo");
        cliente.setApellidos("Blopo Tropo");
        cliente.setDni("53047174V");
        cliente.setDireccion("Calle de ningún lado 12, puerta 3");
        cliente.setEdad(25);
        List<Cuenta_bancaria> cuentas = new ArrayList<Cuenta_bancaria>();
        cuentas.add(cc);
        cuentas.add(ca);
        cliente.setCuentas(cuentas);

        em.persist(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Asierto");
        cliente2.setApellidos("Pussier Lueño");
        cliente2.setDni("17348874V");
        cliente2.setDireccion("Calle de algún lado 12");
        cliente2.setEdad(42);
        List<Cuenta_bancaria> cuentas2 = new ArrayList<Cuenta_bancaria>();
        cuentas2.add(cc);
        cliente2.setCuentas(cuentas2);

        em.persist(cliente2);

        Efectivo ef = new Efectivo();
        ef.setOficina(of);
        ef.setCantidad(200);
        ef.setDescripcion("Transaccion efectiva de prueba");
        ef.setIban_origen(ca);

        em.persist(ef);

        Transferencia tr = new Transferencia();
        tr.setIban_receptora(ca);
        tr.setIban_origen(cc);
        tr.setCantidad(50);
        tr.setDescripcion("Transferencia de prueba");

        em.persist(tr);


		trans.commit();
		em.close();
		entityManagerFactory.close();		
	}
}
