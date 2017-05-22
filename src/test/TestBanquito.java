package test;

import javax.persistence.*;
import entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBanquito
{

	public static void main(String[] args) 
	{
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("Banquito");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String[] abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] tiposCalle = {"Paseo","Calle","Avenida","Cruce"};
		String[] nombreCalle = {"La bomba","Paraiso","Independencia","El Salvador"};
		String[] extra = {"escalera","puerta","piso"};
		String[] nombre = {"Pablo", "Asier", "Jorge", "Alberto", "Juan", "Dario", "David", "Ruben"};
		String[] apellido = {"Salueña", "Sediles", "Sanz", "Piedrafita", "Castañeda", "Martinez", "Perez", "Sanchez", "Nicuela"};
		Random r = new Random();

		Oficina[] of = new Oficina[100];
		Cuenta_ahorro[] cuentasAhorro = new Cuenta_ahorro[100];
		Cuenta_corriente[] cuentasCorrientes = new Cuenta_corriente[100];
		Cliente[] clientes1 = new Cliente[100];
		Cliente[] clientes2 = new Cliente[100];
		Efectivo[] ef = new Efectivo[100];
		Transferencia[] tr = new Transferencia[100];

		trans.begin();

		for (int i= 0; i<100; i++){
		    of[i] = new Oficina();
		    of[i].setDireccion(tiposCalle[r.nextInt(4)]+" "+nombreCalle[r.nextInt(4)]+", "+extra[r.nextInt(3)]+" 1º");
		    of[i].setTelefono(r.nextInt((999999999 - 800000000) + 1) + 800000000);
		    em.persist(of[i]);

            cuentasAhorro[i]= new Cuenta_ahorro();
            cuentasAhorro[i].setTipo_interes(10 * r.nextDouble());
            cuentasAhorro[i].setSaldo(10+ (100000 - 10) * r.nextDouble());
            String iban = "";
            for(int j=0; j<34 ; j++){
                iban+=r.nextInt(10);
            }
            cuentasAhorro[i].setIban(iban);

            em.persist(cuentasAhorro[i]);

		    cuentasCorrientes[i] = new Cuenta_corriente();
		    cuentasCorrientes[i].setOficina(of[i]);
            cuentasCorrientes[i].setSaldo(10+ (100000 - 10) * r.nextDouble());
            iban = "";
            for(int j=0; j<34 ; j++){
                iban+=r.nextInt(10);
            }
            cuentasCorrientes[i].setIban(iban);

            em.persist(cuentasCorrientes[i]);

            clientes1[i] = new Cliente();
            clientes1[i].setNombre(nombre[r.nextInt(8)]);
            clientes1[i].setApellidos(apellido[r.nextInt(9)]);
            clientes1[i].setDni((r.nextInt((99999999 - 10000000)+1)+10000000)+abc[r.nextInt(26)]);
            clientes1[i].setDireccion(tiposCalle[r.nextInt(4)]+" "+nombreCalle[r.nextInt(4)]+", "+extra[r.nextInt(3)]+" 1º");
            clientes1[i].setEdad(r.nextInt((100 - 16) + 1) + 16);
            List<Cuenta_bancaria> cuentas = new ArrayList<Cuenta_bancaria>();
            cuentas.add(cuentasCorrientes[i]);
            cuentas.add(cuentasAhorro[i]);
            clientes1[i].setCuentas(cuentas);

            em.persist(clientes1[i]);

            clientes2[i] = new Cliente();
            clientes2[i].setNombre(nombre[r.nextInt(8)]);
            clientes2[i].setApellidos(apellido[r.nextInt(9)]);
            clientes2[i].setDni((r.nextInt((99999999 - 10000000)+1)+10000000)+abc[r.nextInt(26)]);
            clientes2[i].setDireccion(tiposCalle[r.nextInt(4)]+" "+nombreCalle[r.nextInt(4)]+", "+extra[r.nextInt(3)]+" 1º");
            clientes2[i].setEdad(r.nextInt((100 - 16) + 1) + 16);
            cuentas = new ArrayList<Cuenta_bancaria>();
            cuentas.add(cuentasCorrientes[i]);
            clientes2[i].setCuentas(cuentas);

            em.persist(clientes2[i]);

            ef[i] = new Efectivo();
            ef[i].setOficina(of[i]);
            ef[i].setCantidad(5000*r.nextDouble());
            ef[i].setDescripcion("Efectivo nº"+i);
            ef[i].setIban_origen(cuentasAhorro[i]);

            em.persist(ef[i]);

            tr[i] = new Transferencia();
            tr[i].setIban_receptora(cuentasAhorro[i]);
            tr[i].setIban_origen(cuentasCorrientes[i]);
            tr[i].setCantidad(5000*r.nextDouble());
            tr[i].setDescripcion("Transferencia nº"+i);

            em.persist(tr[i]);
        }

        trans.commit();
        em.close();
        entityManagerFactory.close();

	}
}
