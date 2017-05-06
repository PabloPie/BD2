package test;

import javax.persistence.*;
import entidades.*;

public class TestCliente 
{
	public static void main(String[] args) 
	{
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("EjemploDeUnidadDePersistencia");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();

		Cliente cliente = new Cliente();
		
		em.persist(cliente);
		trans.commit();
		
		em.close();
		entityManagerFactory.close();		
	}
}
