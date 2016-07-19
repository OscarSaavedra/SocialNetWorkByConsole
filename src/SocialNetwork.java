import com.google.common.collect.*;

import java.util.*;

public class SocialNetwork{

    private static final String reset = "\u001B[0m";
    private static final String rojo= "\u001B[31m";
    private static final String azul = "\u001B[34m";

    private Map<String ,Persona> personsByName =new HashMap<>();
    private Map<Long,Persona> personsById=new HashMap<>();
    private BiMap<Persona, Persona>biMapParejas= HashBiMap.create();
    private ListMultimap<Persona,Persona> multimapAmigos= ArrayListMultimap.create();

    public List<Persona> getConexionPath(String pers3, String pers4) {
        Persona personaDestino = personsByName.get(pers4);
        Persona primeroLista=personsByName.get(pers3);

        Set<Persona> visitado = new HashSet<>();
        Map<Persona, Persona> predecesor = new HashMap<>();

        List camino = new LinkedList();
        Queue<Persona> cola = new LinkedList();

        cola.add(primeroLista);
        visitado.add(primeroLista);

        boolean encontrado=false;
            while(!cola.isEmpty()){
                primeroLista = cola.poll();
                if (primeroLista.equals(personaDestino)){
                    encontrado=true;
                    break;
                }else{
                    for(Persona amigo : getFriends(primeroLista)){
                        if(!visitado.contains(amigo)){
                            cola.add(amigo);
                            visitado.add(amigo);
                            predecesor.put(amigo, primeroLista);
                        }
                    }
                }
            }

            if (encontrado){
                for(Persona persona = personaDestino; persona != null; persona = predecesor.get(persona)) {
                    camino.add(persona);
                }
                Collections.reverse(camino);
                }else{
                System.out.println("No hay conexión posible o una de las personas no existe");
            }
            return camino;
        }




    public Integer getConexionDegree(String pers5, String pers6) {
        Persona primeroLista=personsByName.get(pers5);
        Persona personaDestino = personsByName.get(pers6);

        HashBiMap<Integer,Persona>distanciaDeInicio=HashBiMap.create();
        int distancia=0;
        distanciaDeInicio.put(0,primeroLista);

        Queue<Persona> cola=new LinkedList<>();
        HashSet<Persona> visitado = new HashSet<>();
        visitado.add(primeroLista);

        globalLoop:
        while (primeroLista != null) {
            distancia++;
            for (Persona friend : getFriends(primeroLista)) {
                if (friend.equals(personaDestino)||(personaDestino.equals(getCouple(primeroLista)))) {
                    distanciaDeInicio.put(distancia,personaDestino);
                    break globalLoop;
                }
                if (!visitado.contains(friend)) {
                    visitado.add(friend);
                    cola.offer(friend);
                    distanciaDeInicio.put(distancia,friend);
                }
            }
            primeroLista = cola.poll();
        }
        return distanciaDeInicio.inverse().get(personaDestino);
    }


    public int getConexionMismoGrafo(String pers1, String pers2){
        Persona persona=personsByName.get(pers1);
        Persona anotherPersona=personsByName.get(pers2);
        List<Persona>listaConexion=getFriends(persona);
        List<Persona>listaConexion2=getFriends(anotherPersona);
        boolean confirmacion1=false;
        boolean confirmacion2=false;
        int conectados=0;
        for (Persona elementos:listaConexion) {
            if (getFriends(elementos)!=null){
                confirmacion1=true;
            }
        }
        for (Persona elementos:listaConexion2) {
            if (getFriends(elementos)!=null){
                confirmacion2=true;
            }
        }

        if(confirmacion1&&confirmacion2){
            conectados=1;
        }else{
            conectados=0;
        }
        return conectados;
    }
    public void addPerson(Persona persona){
        personsByName.put(persona.getName(),persona);
        personsById.put(persona.getId(),persona);
    }
    public Persona getPersona(Long id){
        return personsById.get(id);
        }
    public Persona getPersona(String name){
        return personsByName.get(name);
    }


    public void addCouple(Persona persona,Persona anotherPersona){
        checkIfCouple(persona);
        checkIfCouple(anotherPersona);
        biMapParejas.put(persona,anotherPersona);
    }
    public Persona getCouple(Persona persona){
        if(biMapParejas.get(persona)==null){
            return (biMapParejas.inverse().get(persona));
        }else{
            return (biMapParejas.get(persona));
        }

    }
    private void checkIfCouple(Persona persona) {
        if (biMapParejas.containsKey(persona)
                || biMapParejas.values().contains(persona)){
            System.out.println("La persona "+persona.getName()+" ya tiene pareja");
            throw new RuntimeException("La persona "+persona.getName()+" ya tiene pareja");
        }
    }


    public void addFriendship(Persona persona,Persona anotherPersona){
        multimapAmigos.put(persona,anotherPersona);
    }
    public List<Persona> getFriends(Persona persona){
        return (multimapAmigos.get(persona));
    }
    public String getFriendsQuantity(String cantidadAmigosDe){
        Persona p7=getPersona(cantidadAmigosDe);//obtengo el objeto persona por la string de consola
        List<Persona>p8=getFriends(p7); //creo una lista p8 de amigos de p7
        int contadorAmigos=0;
        for (Persona lista:p8){//recorro la lista de amigos y en cada vuelta suma al contador
            contadorAmigos++;
        }
        return contadorAmigos+" los cuales son "+p8;
    }

    public List<Persona> getCouplesFriends (String amigosParejaDe){
        Persona p2=getPersona(amigosParejaDe);//obtebemos el objeto persona
        Persona p3=getCouple(p2);//obtenemos el objeto persona que es pareja de p2
        List<Persona>p4=getFriends(p3);//obtenemos la List de amigos de p3 (p3 es pareja de p2)
        return p4;
    }
    public String getFriendsCouples(String parejasAmigosDe) {
        Persona p5=getPersona(parejasAmigosDe);//buscamos el objeto persona por la string
        List<Persona> p6=getFriends(p5);//buscamos los amigos de la persona
        for (Persona lista :p6) { //abrimos una lista con todos los amigos (p6) y para cada elemento de la lista
                                //hacemos getCouple
            if ((getCouple(lista))==null){
                System.out.println("Uno de los amigos de "+parejasAmigosDe+" no tiene pareja");
            }else{
                System.out.println("La parejas de los amigos de "+parejasAmigosDe+" son: "+getCouple(lista));
            }
        }
        return "";
    }

    public List<Persona> getFriendsRank() {
        List<Persona> personaList=new ArrayList<>(personsByName.values());

        Collections.sort(personaList, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                int num1=getFriends(p1).size();
                int num2=getFriends(p2).size();
                if (num1<num2){
                    return 1;
                }else if(num2>num1){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        return personaList;
    }

    public String getMenu(){
        return "[1]"+azul+" Consultar personas por Nombre"+reset+"\n"
        +"[2]"+azul+" Consultar personas por ID"+reset+"\n"
        +"[3]"+azul+" Consultar los amigos de una persona"+reset+"\n"
        +"[4]"+azul+" Consultar la pareja de una persona"+reset+"\n"
        +"[5]"+azul+" Consultar los amigos de la pareja de una persona"+reset+"\n"
        +"[6]"+azul+" Consultar las parejas de de los amigos de una persona"+reset+"\n"
        +"[7]"+azul+" Mostrar la cantidad de amigos de una persona"+reset+"\n"
        +"[8]"+azul+" Mostrar el ranking de personas con mas amigos"+reset+"\n"
        +"[9]"+azul+" Averiguar si dos personas pertenecen al mismo grafo"+reset+"\n"
        +"[10]"+azul+" Camino entre dos personas"+reset+"\n"
        +"[11]"+azul+" Grado de conexión entre dos personas (incluyendo conexión de parejas)"+reset+"\n"
        +"[12]"+rojo+" Salir"+reset+"\n";
    }
}
