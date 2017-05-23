package test;

import javax.persistence.*;

import entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBanquito {

    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager em;
    public static final String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static final String[] tiposCalle = {"Paseo", "Calle", "Avenida", "Cruce"};
    public static final String[] nombreCalle = {"La bomba", "Paraiso", "Independencia", "El Salvador"};
    public static final String[] extra = {"escalera", "puerta", "piso"};
    public static final String[] nombre = {"Pablo", "Asier", "Jorge", "Alberto", "Juan", "Dario", "David", "Ruben"};
    public static final String[] apellido = {"Salueña", "Sediles", "Sanz", "Piedrafita", "Castañeda", "Martinez", "Perez", "Sanchez", "Nicuela"};

    public static Oficina[] of = new Oficina[100];
    public static Cuenta_ahorro[] cuentasAhorro = new Cuenta_ahorro[100];
    public static Cuenta_corriente[] cuentasCorrientes = new Cuenta_corriente[100];
    public static Cliente[] clientes1 = new Cliente[100];
    public static Cliente[] clientes2 = new Cliente[100];
    public static Efectivo[] ef = new Efectivo[100];
    public static Transferencia[] tr = new Transferencia[100];

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("Banquito");
        em = entityManagerFactory.createEntityManager();

        generarDatos();

        System.out.println("JPQL:");
        em.getTransaction().begin();
        consulta1(clientes1[0].getDni()); //Coger un dni de la tabla de clientes

        consulta2(clientes1[0].getDni());

        consulta3(10);

        consulta4(cuentasAhorro[0].getIban());

        consulta5();
        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();

    }

    private static void consulta1(String dni) {

        System.out.println("Para un dni devuelve para cada una de sus cuentas su iban, saldo y tipo de cuenta");
        TypedQuery<Object[]> q = em.createQuery("SELECT C.iban, C.saldo, 'CUENTA_CORRIENTE' FROM CUENTAS C , IN (C.clientes) CC WHERE CC.dni=:dni AND TYPE(C)=CUENTA_CORRIENTE", Object[].class).setParameter("dni", dni);
        //.setParameter("dni",dni);

        List<Object[]> l = q.getResultList();
        q = em.createQuery("SELECT C.iban, C.saldo, 'CUENTA_AHORRO' FROM CUENTAS C , IN (C.clientes) CC WHERE CC.dni=:dni AND TYPE(C)=CUENTA_AHORRO", Object[].class).setParameter("dni", dni);
        l.addAll(q.getResultList());

        for (Object[] o : l) {
            System.out.println(o[0] + "  " + o[1] + "  " + o[2]);
        }

    }

    private static void consulta2(String dni) {

        System.out.println("Para un dni todas las operaciones que se han realizado en alguna de sus cuentas");
        Query q = em.createQuery("SELECT O FROM CUENTAS C , IN (C.clientes) CC, OPERACIONES O WHERE CC.dni=:dni AND O.iban_origen=C.iban").setParameter("dni", dni);

        List<Operacion> operaciones = q.getResultList();
        for (Operacion o : operaciones) {
            System.out.println(o);
        }

    }

    private static void consulta3(int id) {

        System.out.println("Devuelve el DNI de todos los clientes de una oficina");
        Query q = em.createQuery("SELECT CC.dni FROM CUENTAS C , IN (C.clientes) CC, IN (C.oficina) O WHERE O.idoficina=:id").setParameter("id", id);

        List<String> dnis = q.getResultList();
        for (String dni : dnis) {
            System.out.println(dni);
        }
    }

    private static void consulta4(String iban) {

        System.out.println("Devuelve el dinero recibido por transferencias para un iban especifico");
        Query q = em.createQuery("SELECT SUM(T.cantidad) FROM TRANSFERENCIA T, IN(T.iban_receptora) C WHERE C.iban=:iban GROUP BY C.iban").setParameter("iban", iban);

        List<Double> money = q.getResultList();
        for (Double m : money) {
            System.out.println(m);
        }
    }

    private static void consulta5() {

        System.out.println("Cuentas que han realizado operaciones en una oficina distinta de la asignada");
        Query q = em.createQuery("SELECT E FROM CUENTA_CORRIENTE C, EFECTIVO E WHERE E.iban_origen=C AND E.oficina!=C.oficina");

        List<Operacion> money = q.getResultList();
        for (Operacion m : money) {
            System.out.println(m);
        }
    }

    public static void generarDatos() {
        EntityTransaction trans = em.getTransaction();
        Random r = new Random();

        trans.begin();

        for (int i = 0; i < 100; i++) {
            of[i] = new Oficina();
            of[i].setDireccion(tiposCalle[r.nextInt(4)] + " " + nombreCalle[r.nextInt(4)] + ", " + extra[r.nextInt(3)] + " 1º");
            of[i].setTelefono(r.nextInt((999999999 - 800000000) + 1) + 800000000);
            em.persist(of[i]);

            cuentasAhorro[i] = new Cuenta_ahorro();
            cuentasAhorro[i].setTipo_interes(10 * r.nextDouble());
            cuentasAhorro[i].setSaldo(10 + (100000 - 10) * r.nextDouble());
            String iban = "";
            for (int j = 0; j < 34; j++) {
                iban += r.nextInt(10);
            }
            cuentasAhorro[i].setIban(iban);

            em.persist(cuentasAhorro[i]);

            cuentasCorrientes[i] = new Cuenta_corriente();
            cuentasCorrientes[i].setOficina(of[i]);
            cuentasCorrientes[i].setSaldo(10 + (100000 - 10) * r.nextDouble());
            iban = "";
            for (int j = 0; j < 34; j++) {
                iban += r.nextInt(10);
            }
            cuentasCorrientes[i].setIban(iban);

            em.persist(cuentasCorrientes[i]);

            clientes1[i] = new Cliente();
            clientes1[i].setNombre(nombre[r.nextInt(8)]);
            clientes1[i].setApellidos(apellido[r.nextInt(9)]);
            clientes1[i].setDni((r.nextInt((99999999 - 10000000) + 1) + 10000000) + abc[r.nextInt(26)]);
            clientes1[i].setDireccion(tiposCalle[r.nextInt(4)] + " " + nombreCalle[r.nextInt(4)] + ", " + extra[r.nextInt(3)] + " 1º");
            clientes1[i].setEdad(r.nextInt((100 - 16) + 1) + 16);
            List<Cuenta_bancaria> cuentas = new ArrayList<Cuenta_bancaria>();
            cuentas.add(cuentasCorrientes[i]);
            cuentas.add(cuentasAhorro[i]);
            clientes1[i].setCuentas(cuentas);

            em.persist(clientes1[i]);

            clientes2[i] = new Cliente();
            clientes2[i].setNombre(nombre[r.nextInt(8)]);
            clientes2[i].setApellidos(apellido[r.nextInt(9)]);
            clientes2[i].setDni((r.nextInt((99999999 - 10000000) + 1) + 10000000) + abc[r.nextInt(26)]);
            clientes2[i].setDireccion(tiposCalle[r.nextInt(4)] + " " + nombreCalle[r.nextInt(4)] + ", " + extra[r.nextInt(3)] + " 1º");
            clientes2[i].setEdad(r.nextInt((100 - 16) + 1) + 16);
            cuentas = new ArrayList<Cuenta_bancaria>();
            cuentas.add(cuentasCorrientes[i]);
            clientes2[i].setCuentas(cuentas);

            em.persist(clientes2[i]);

            ef[i] = new Efectivo();
            ef[i].setOficina(of[i]);
            ef[i].setCantidad(5000 * r.nextDouble());
            ef[i].setDescripcion("Efectivo nº" + i);
            ef[i].setIban_origen(cuentasAhorro[i]);

            em.persist(ef[i]);

            tr[i] = new Transferencia();
            tr[i].setIban_receptora(cuentasAhorro[i]);
            tr[i].setIban_origen(cuentasCorrientes[i]);
            tr[i].setCantidad(5000 * r.nextDouble());
            tr[i].setDescripcion("Transferencia nº" + i);
            em.persist(tr[i]);
        }

        Efectivo e = new Efectivo();
        e.setIban_origen(cuentasCorrientes[4]);
        e.setDescripcion("Operacion rara");
        e.setCantidad(1.2);
        e.setOficina(of[13]);

        em.persist(e);

        trans.commit();
    }
}
