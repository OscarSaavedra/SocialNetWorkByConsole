import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Persona cristina = new Persona("Cristina", " ", 1L);
        Persona juan = new Persona("Juan", " ", 2L);
        Persona carol = new Persona("Carol", " ", 3L);
        Persona marc = new Persona("Marc", " ", 4L);
        Persona julia = new Persona("Julia", " ", 5L);
        Persona pedro = new Persona("Pedro", " ", 6L);
        Persona ana = new Persona("Ana", " ", 7L);
        Persona antonio = new Persona("Antonio", " ", 8L);


        //Persona roberto=new Persona("Roberto","",9L);
        //Persona pepito=new Persona("Pepito","",10L);


        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.addPerson(cristina);
        socialNetwork.addPerson(juan);
        socialNetwork.addPerson(carol);
        socialNetwork.addPerson(marc);
        socialNetwork.addPerson(julia);
        socialNetwork.addPerson(pedro);
        socialNetwork.addPerson(ana);
        socialNetwork.addPerson(antonio);


        //socialNetwork.addPerson(roberto);
        //socialNetwork.addPerson(pepito);
        //socialNetwork.addFriendship(roberto,pepito);




        socialNetwork.addFriendship(cristina,pedro);

        socialNetwork.addFriendship(juan, marc);
        socialNetwork.addFriendship(juan, antonio);

        socialNetwork.addFriendship(marc, juan);
        socialNetwork.addFriendship(marc, antonio);

        socialNetwork.addFriendship(carol, julia);

        socialNetwork.addFriendship(julia, carol);
        socialNetwork.addFriendship(julia, ana);
        socialNetwork.addFriendship(julia, pedro);

        socialNetwork.addFriendship(pedro,julia);

        socialNetwork.addFriendship(ana, julia);
        socialNetwork.addFriendship(ana, antonio);

        socialNetwork.addFriendship(antonio, ana);
        socialNetwork.addFriendship(antonio, juan);


        socialNetwork.addCouple(cristina, juan);
        socialNetwork.addCouple(marc, ana);
        socialNetwork.addCouple(pedro, antonio);

        //socialNetwork.biMapParejas.put(marc,julia);si hacemos el bimap pareja publico, podemos hacer esto y se salta el check

        boolean bucle=true;
        while (bucle) {
            System.out.println(socialNetwork.getMenu());
            int eleccion = sc.nextInt();
            switch (eleccion) {
                case 1:
                    System.out.println("Nombre de la persona:");
                    String buscaNombre = sc.next();
                    System.out.println("Persona: " + socialNetwork.getPersona(buscaNombre));
                    break;
                case 2:
                    System.out.println("Id de la persona:");
                    Long buscaID = sc.nextLong();
                    System.out.println("Persona con id " + buscaID + " " + socialNetwork.getPersona(buscaID));
                    break;
                case 3:
                    System.out.println("Mostrar los amigos de:");
                    String amigosDe = sc.next();
                    Persona persona = socialNetwork.getPersona(amigosDe);
                    System.out.println(socialNetwork.getFriends(persona));
                    break;
                case 4:
                    System.out.println("Consultar la pareja de:");
                    String parejaDe = sc.next();
                    Persona pareja = socialNetwork.getPersona(parejaDe);
                    System.out.println(socialNetwork.getCouple(pareja));
                    break;
                case 5:
                    System.out.println("Consultar los amigos de la pareja de:");
                    String amigosParejaDe=sc.next(); //creo un String
                    System.out.println(socialNetwork.getCouplesFriends(amigosParejaDe)); //le paso el parametro String
                                                                                         //para que lo use en el metodo
                                                                                        //y devuelva p4
                    break;
                case 6:
                    System.out.println("Consultar las parejas de los amigos de:");
                    String parejasAmigosDe=sc.next();
                    System.out.println(socialNetwork.getFriendsCouples(parejasAmigosDe));
                    break;
                case 7:
                    System.out.println("Mostrar la cantidad de amigos de la persona: ");
                    String cantidadAmigosDe=sc.next();
                    System.out.println(socialNetwork.getFriendsQuantity(cantidadAmigosDe));
                    break;
                case 8:
                    System.out.println("Ranking de personas con mas amigos");
                    System.out.println(socialNetwork.getFriendsRank());
                    break;
                case 9:
                    System.out.println("Comprueba si dos personas están conectadas de alguna forma en el mismo grafo");
                    System.out.println("Persona 1:");
                    String pers1=sc.next();
                    System.out.println("Persona 2:");
                    String pers2=sc.next();
                    System.out.println(socialNetwork.getConexionMismoGrafo(pers1,pers2));
                    break;
                case 10:
                    System.out.println("Camino entre dos personas");
                    System.out.println("Persona 1:");
                    String pers3=sc.next();
                    System.out.println("Persona 2:");
                    String pers4=sc.next();
                    System.out.println(socialNetwork.getConexionPath(pers3,pers4));
                    break;
                case 11:
                    System.out.println("Grado de conexión entre dos personas");
                    System.out.println("Persona 1:");
                    String pers5=sc.next();
                    System.out.println("Persona 2:");
                    String pers6=sc.next();
                    System.out.println(socialNetwork.getConexionDegree(pers5,pers6));
                    break;
                case 12:
                    bucle=false;
                    break;
            }
        }

    }










}

