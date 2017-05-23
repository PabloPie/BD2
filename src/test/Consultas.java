package test;

import javax.persistence.*;
import entidades.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consultas
{

    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager em;

    public static void main(String[] args)
    {
        entityManagerFactory =  Persistence.createEntityManagerFactory("Banquito");
        em = entityManagerFactory.createEntityManager();

        EntityTransaction trans = em.getTransaction();
        trans.begin();

        System.out.println("JPQL:");
        consulta1("25664888s");


        trans.commit();
        em.close();
        entityManagerFactory.close();

    }

    private static void consulta1(String dni) {
        System.out.println("Para un dni devuelve la cuenta asociada que más operaciones haya efectuado ");
        Query q = em.createQuery("SELECT C FROM Cuenta_bancaria C");
                //.setParameter("dni",dni);
        List<Cuenta_bancaria[]> cuentas = q.getResultList();
        //System.out.println("CUENTA,TIPO,CONTADOR");
        System.out.println(cuentas);

        /*for (Cuenta_bancaria [] c: cuentas) {
            for(Object one: c){
                System.out.print(one + ",");
            }
            System.out.println();
        }*/
    }


    /*private static void consulta2(int oficina) {
        System.out.println("Devuelve usarios que pertenecen a la oficina indicada");
        Query q = em.createQuery("")
                .setParameter("oficina",oficina);
        List<Usuario> usuarios = q.getResultList();
        System.out.println(usuarios.size());
        for (Usuario c: usuarios) {
            System.out.println(c);
        }
    }*/
}
