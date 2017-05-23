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

        consulta1("79914947v");

        consulta2("79914947v");

        consulta3(10);

        consulta4("4353575552702875657273877968572052");

        consulta5();


        trans.commit();
        em.close();
        entityManagerFactory.close();

    }

    private static void consulta1(String dni) {

        System.out.println("Para un dni devuelve para cada una de sus cuentas su iban, saldo y tipo de cuenta");
        TypedQuery<Object[]> q = em.createQuery("SELECT C.iban, C.saldo, 'CUENTA_CORRIENTE' FROM CUENTAS C , IN (C.clientes) CC WHERE CC.dni=:dni AND TYPE(C)=CUENTA_CORRIENTE", Object[].class).setParameter("dni",dni);
                //.setParameter("dni",dni);

        List<Object[]> l = q.getResultList();



        q = em.createQuery("SELECT C.iban, C.saldo, 'CUENTA_AHORRO' FROM CUENTAS C , IN (C.clientes) CC WHERE CC.dni=:dni AND TYPE(C)=CUENTA_AHORRO", Object[].class).setParameter("dni",dni);


        l.addAll(q.getResultList());

        for(Object[] o:l){
            System.out.println(o[0]+"  "+o[1]+"  "+o[2]);
        }

    }

    private static void consulta2(String dni) {

        System.out.println("Para un dni todas las operaciones que se han realizado en alguna de sus cuentas");
        Query q = em.createQuery("SELECT O FROM CUENTAS C , IN (C.clientes) CC, OPERACIONES O WHERE CC.dni=:dni AND O.iban_origen=C.iban").setParameter("dni",dni);

        List<Operacion> operaciones = q.getResultList();
        for(Operacion o:operaciones){
            System.out.println(o);
        }

    }




    private static void consulta3(int id) {

        System.out.println("Devuelve el DNI de todos los clientes de una oficina");
        Query q = em.createQuery("SELECT CC.dni FROM CUENTAS C , IN (C.clientes) CC, IN (C.oficina) O WHERE O.idoficina=:id").setParameter("id",id);

        List<String> dnis = q.getResultList();
        for(String dni:dnis){
            System.out.println(dni);
        }
    }

    private static void consulta4(String iban) {

        System.out.println("Devuelve el dinero recibido por transferencias para un iban especifico");
        Query q = em.createQuery("SELECT SUM(T.cantidad) FROM TRANSFERENCIA T, IN(T.iban_receptora) C WHERE C.iban=:iban GROUP BY C.iban").setParameter("iban",iban);

        List<Double> money = q.getResultList();
        for(Double m:money){
            System.out.println(m);
        }
    }

    private static void consulta5() {

        System.out.println("Cuentas que han realizado operaciones en una oficina distinta de la asignada");
        Query q = em.createQuery("SELECT E FROM CUENTA_CORRIENTE C, EFECTIVO E WHERE E.iban_origen=C AND E.oficina!=C.oficina");

        List<Operacion> money = q.getResultList();
        for(Operacion m:money){
            System.out.println(m);
        }
    }

}
