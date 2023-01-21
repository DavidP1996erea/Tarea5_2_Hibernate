package com.example.tarea5_2_hibernate;


import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    // se crea una variable estática para poder hacer operaciones con la base de datos
    private  static SessionFactory sessionFactory;
    private  static  Session session;
    public static void main(String[] args) {

        // Se llama a los métodos
        try {
            setUp();
            session = sessionFactory.openSession();
            menu();
            sessionFactory.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * Menú con las diferentes opciones del crud. Cada opción llama al método correspondiente que
     * realizará la operación necesaria.
     */
    private static void menu(){

        Scanner sc = new Scanner(System.in);
        int opcion;

        opciones();
        opcion=sc.nextInt();

        while (opcion!=13){

            switch (opcion){
                /**  Parte personas */
                case 1:
                    Scanner sc2 = new Scanner(System.in);
                    // Se piden los datos al usuario
                    System.out.println("Introduce su nick");
                    String nick = sc2.nextLine();
                    System.out.println("Introduce su contraseña");
                    String password = sc2.nextLine();
                    System.out.println("Introduce su email");
                    String email = sc2.nextLine();

                    guardarPersona(nick, password, email);
                    break;

                case 2:
                    System.out.println("Introduzca el id de la persona a eliminar");
                    int idEliminar = sc.nextInt();
                    eliminarPersona(idEliminar);
                    break;

                case 3:
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Introduzca el id de la persona a actualizar");
                    int idActualizar = sc.nextInt();

                    // Se piden los datos al usuario
                    System.out.println("Introduce su nick");
                    String nickUpdate = sc3.nextLine();
                    System.out.println("Introduce su contraseña");
                    String passwordUpdate = sc3.nextLine();
                    System.out.println("Introduce su email");
                    String emailUpdate = sc3.nextLine();

                    actualizarPersona(idActualizar, nickUpdate, passwordUpdate , emailUpdate);
                    break;

                case 4:

                    mostrarDatosPersona();

                    break;


                    /**  Parte juegos */


                case 5:
                    Scanner sc4 = new Scanner(System.in);
                    // Se piden los datos al usuario
                    System.out.println("Introduce el nombre del juego");
                    String nombreJuego = sc4.nextLine();
                    System.out.println("Introduce el tiempo de juego en formato: HH:MM:SS");
                    Time tiempoJuego = Time.valueOf(sc4.nextLine());


                    guardarGame(nombreJuego, tiempoJuego);
                    break;

                case 6:
                    System.out.println("Introduzca el id del juego a eliminar");
                    int idEliminarJuego = sc.nextInt();
                    eliminarGame(idEliminarJuego);
                    break;

                case 7:
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Introduzca el id del juego a actualizar");
                    int idActualizarJuego = sc.nextInt();

                    // Se piden los datos al usuario
                    System.out.println("Introduce el nombre del juego");
                    String nombreJuegoActualizar = sc5.nextLine();
                    System.out.println("Introduce el nuevo tiempo de juego");
                    Time tiempoJuegoActualizar = Time.valueOf(sc5.nextLine());

                    actualizarGame(idActualizarJuego, nombreJuegoActualizar, tiempoJuegoActualizar );
                    break;

                case 8:

                    mostrarDatosGame();

                    break;

                /**  Parte Compras */

                case 9:
                    Scanner sc6 = new Scanner(System.in);
                    // Se piden los datos al usuario
                    System.out.println("Introduce el id del jugador");
                    int id_Player = sc6.nextInt();
                    System.out.println("Introduce el id del juego");
                    int id_Games = sc6.nextInt();
                    System.out.println("Introduce la cosa");
                    int cosaInsertar = sc6.nextInt();
                    System.out.println("Introduce el precio");
                    double precioInsertar = sc6.nextDouble();
                    System.out.println("Introduce la fecha en el formato: yyyy-MM-dd ");
                    String fecha = sc.nextLine();


                    guardarCompras(id_Player, id_Games, cosaInsertar, precioInsertar,  recogerFecha(fecha));
                    break;

                case 10:
                    System.out.println("Introduzca el id de la compra a eliminar");
                    int idEliminarCompra = sc.nextInt();
                    eliminarCompras(idEliminarCompra);
                    break;

                case 11:
                    Scanner sc7 = new Scanner(System.in);
                    System.out.println("Introduzca el id de la compra a actualizar");
                    int idActualizarCompra= sc.nextInt();


                    // Se piden los datos al usuario
                    System.out.println("Introduce el id del jugador");
                    int id_PlayerActualizar = sc7.nextInt();
                    System.out.println("Introduce el id del juego");
                    int id_GamesActualizar = sc7.nextInt();
                    System.out.println("Introduce la cosa");
                    int cosaActualizar = sc7.nextInt();
                    System.out.println("Introduce el precio");
                    double precioActualizar = sc7.nextDouble();
                    System.out.println("Introduce la fecha en el formato: yyyy-MM-dd ");
                    String fechaActualizar = sc7.nextLine();

                    actualizarCompras(idActualizarCompra, id_PlayerActualizar, id_GamesActualizar, cosaActualizar,precioActualizar,recogerFecha(fechaActualizar) );
                    break;

                case 12:

                    mostrarDatosCompras();

                    break;


                default:
                    System.out.println("Opción incorrecta");
            }

            opciones();
            opcion=sc.nextInt();

        }

    }

    /**
     * Método que muestra en pantalla las opciones del menu
     */
    private static void opciones(){
        System.out.println("1. Insertar persona");
        System.out.println("2. Eliminar persona");
        System.out.println("3. Actualizar persona ");
        System.out.println("4. Mostrar datos de jugadores");

        System.out.println("5. Insertar juego");
        System.out.println("6. Eliminar juego ");
        System.out.println("7. Actualizar juego ");
        System.out.println("8. Mostrar datos de juegos");


        System.out.println("9. Insertar compra");
        System.out.println("10. Eliminar compra ");
        System.out.println("11. Actualizar compra ");
        System.out.println("12. Mostrar datos de compra");


        System.out.println("13. Salir");
    }

    /**
     * Con este método se inicia y se prepara todo para la conexión con la base de datos
     * @throws Exception
     */
    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }



    /**   Manjeadora PlayerEntity    */


    /**
     * Se pide como parámetro los datos de la persona que se va a insertar, esos datos se meten en el constructor
     * del objeto tipo PlayerEntidad. Una vez que se crea el objeto con los datos introducidos, se crea una
     * transacción y se guarda la persona.
     * @param nick
     * @param password
     * @param email
     */
    private static void guardarPersona(String nick, String password, String email ) {


        PlayerEntity persona = new PlayerEntity(nick, password, email);
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);

    }

    /**
     * Método que requiere un int como parámetro que será el id de la persona que se desea eliminar. Se crea un
     * objeto de tipo PlaterEntidad y se recoge la persona con el id que es pasado por parámetro, luego se borra
     * a esa persona.
     * @param id
     */
    private static void eliminarPersona(int id){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        PlayerEntity persona = session.get(PlayerEntity.class, id);
        // Se borra esa persona
        session.delete(persona);
        transaction.commit();

    }


    /**
     * Este método requiere un int como parámetro, que será el id de una persona que se desea modificar. Al igual
     * que en el método eliminar, se obtiene una persona con este id, y una vez que se hacen las modificaciones,
     *  se actualiza dicha persona.
     * @param id
     * @param nickUpdate
     * @param passwordUpdate
     * @param emailUpdate
     */
    private static void actualizarPersona(int id, String nickUpdate, String passwordUpdate, String emailUpdate){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        PlayerEntity persona = session.get(PlayerEntity.class, id);
        // Se cambia el parámetro que se quiera seteando su atributo
        persona.setNick(nickUpdate);
        persona.setPassword(passwordUpdate);
        persona.setEmail(emailUpdate);
        // Se actualiza esa persona
        session.saveOrUpdate(persona);
        transaction.commit();

    }

    /**
     * Con este método obtenemos todos los datos de todas las personas. Primero se crea un objeto tipo CriteriaBuilder
     * donde se hará uso de la session. Luego se crean una serie de objetos para crear la conexión con la tabla
     * correspondiente y para coger todos los datos. Estos datos se recogen en una lista de tipo PlayerEntidad,
     * que se recorrerá con un for each para mostrar los datos.
     */
    public static void mostrarDatosPersona() {


        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PlayerEntity> cq = cb.createQuery(PlayerEntity.class);
        Root<PlayerEntity> rootEntry = cq.from(PlayerEntity.class);
        CriteriaQuery<PlayerEntity> all = cq.select(rootEntry);

        TypedQuery<PlayerEntity> allQuery = session.createQuery(all);

        for (PlayerEntity jugador:   allQuery.getResultList()
        ) {
            System.out.println(jugador.getNick() + " " +  jugador.getPassword()  +  " " +  jugador.getEmail());
        }
        transaction.commit();

    }


    /**   Manjeadora GamesEntity    */


    /**
     * Se pide como parámetro los datos de juego que se va a insertar, esos datos se meten en el constructor
     * del objeto tipo GamesEntity. Una vez que se crea el objeto con los datos introducidos, se crea una
     * transacción y se guarda el juego.
     * @param nombre
     * @param tiempoJugado
     */
    private static void guardarGame(String nombre, Time tiempoJugado ) {


        GamesEntity juego = new GamesEntity(nombre, tiempoJugado);
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(juego);
        transaction.commit();
        System.out.println(id);

    }

    /**
     * Método que requiere un int como parámetro que será el id del juego que se desea eliminar. Se crea un
     * objeto de tipo GamesEntity y se recoge el juego con el id que es pasado por parámetro, luego se borra
     * a ese juego.
     * @param id
     */
    private static void eliminarGame(int id){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        GamesEntity juego = session.get(GamesEntity.class, id);
        // Se borra esa persona
        session.delete(juego);
        transaction.commit();

    }


    /**
     * Este método requiere un int como parámetro, que será el id de un juego que se desea modificar. Al igual
     * que en el método eliminar, se obtiene un juego con este id, y una vez que se hacen las modificaciones,
     *  se actualiza dicho juego.
     * @param id
     * @param nombre
     * @param tiempoJugado
     */
    private static void actualizarGame(int id, String nombre, Time tiempoJugado){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        GamesEntity juego = session.get(GamesEntity.class, id);
        // Se cambia el parámetro que se quiera seteando su atributo
        juego.setNombre(nombre);
        juego.setTiempoJugado(tiempoJugado);

        // Se actualiza esa persona
        session.saveOrUpdate(juego);
        transaction.commit();

    }

    /**
     * Con este método obtenemos todos los datos de todos los juegos. Primero se crea un objeto tipo CriteriaBuilder
     * donde se hará uso de la session. Luego se crean una serie de objetos para crear la conexión con la tabla
     * correspondiente y para coger todos los datos. Estos datos se recogen en una lista de tipo GamesEntity,
     * que se recorrerá con un for each para mostrar los datos.
     */
    public static void mostrarDatosGame() {


        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<GamesEntity> cq = cb.createQuery(GamesEntity.class);
        Root<GamesEntity> rootEntry = cq.from(GamesEntity.class);
        CriteriaQuery<GamesEntity> all = cq.select(rootEntry);

        TypedQuery<GamesEntity> allQuery = session.createQuery(all);

        for (GamesEntity juegos:   allQuery.getResultList()
        ) {
            System.out.println(juegos.getNombre() + " " +  juegos.getTiempoJugado());
        }
        transaction.commit();

    }





    /**   Manjeadora ComprasEntity    */


    /**
     * Se pide como parámetro los datos de la compra que se va a insertar, esos datos se meten en el constructor
     * del objeto tipo ComprasEntity. Una vez que se crea el objeto con los datos introducidos, se crea una
     * transacción y se guarda la compra.
     * @param id_Player
     * @param id_Games
     * @param cosa
     * @param precio
     * @param fechaCompra
     */
    private static void guardarCompras(int id_Player, int id_Games, int cosa, double precio, Date fechaCompra) {


        ComprasEntity compras = new ComprasEntity( cosa,  precio,  fechaCompra);
        compras.setId_Player(session.load(PlayerEntity.class, id_Player));
        compras.setId_Games(session.load(GamesEntity.class, id_Games));
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(compras);
        transaction.commit();
        System.out.println(id);

    }

    /**
     * Método que requiere un int como parámetro que será el id de la compra que se desea eliminar. Se crea un
     * objeto de tipo ComprasEntity y se recoge la compra con el id que es pasado por parámetro, luego se borra
     * a esa compra.
     * @param id
     */
    private static void eliminarCompras(int id){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        ComprasEntity compras = session.get(ComprasEntity.class, id);
        // Se borra esa persona
        session.delete(compras);
        transaction.commit();

    }


    /**
     * Este método requiere un int como parámetro, que será el id de una compra que se desea modificar. Al igual
     * que en el método eliminar, se obtiene una compra con este id, y una vez que se hacen las modificaciones,
     *  se actualiza dicha compra.
     * @param id_Player
     * @param id_Games
     * @param cosa
     * @param precio
     * @param fechaCompra
     */
    private static void actualizarCompras(int indCompras, int id_Player, int id_Games, int cosa, double precio, Date fechaCompra){

        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        ComprasEntity compras = session.get(ComprasEntity.class, indCompras);

        // Se cambia el parámetro que se quiera seteando su atributo
        compras.setCosa(cosa);
        compras.setPrecio(precio);
        compras.setFechaCompra(fechaCompra);

        compras.setId_Player(session.load(PlayerEntity.class, id_Player));
        compras.setId_Games(session.load(GamesEntity.class, id_Games));
        // Se actualiza esa persona
        session.saveOrUpdate(compras);
        transaction.commit();

    }

    /**
     * Con este método obtenemos todos los datos de todas las compras. Primero se crea un objeto tipo CriteriaBuilder
     * donde se hará uso de la session. Luego se crean una serie de objetos para crear la conexión con la tabla
     * correspondiente y para coger todos los datos. Estos datos se recogen en una lista de tipo ComprasEntity,
     * que se recorrerá con un for each para mostrar los datos.
     */
    public static void mostrarDatosCompras() {


        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ComprasEntity> cq = cb.createQuery(ComprasEntity.class);
        Root<ComprasEntity> rootEntry = cq.from(ComprasEntity.class);
        CriteriaQuery<ComprasEntity> all = cq.select(rootEntry);

        TypedQuery<ComprasEntity> allQuery = session.createQuery(all);

        for (ComprasEntity compras:   allQuery.getResultList()
        ) {
            System.out.println("id jugador: "+ compras.getId_Player().getId_Player() + " "+ " id juego: "+ compras.getId_Games().getId_Games() + " "
                  + "Cosa: "+  compras.getCosa() + " " + "Precio: "+  compras.getPrecio() + " "+"Fecha: "+  compras.getFechaCompra());
        }
        transaction.commit();

    }


    /**
     * Método que devuelve un objeto Date. Se pide como parámetro un String que contendrá la fecha,
     * que será parseado a Date y devuelto.
     * @param fecha
     * @return
     */
    public static Date recogerFecha(String fecha){
        Date  fechaEnviar = null;

        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");



            String dateString = format.format( new Date()   );
            fechaEnviar       = format.parse ( fecha );
        } catch (Exception e) {

        }
        return fechaEnviar;
    }

}

