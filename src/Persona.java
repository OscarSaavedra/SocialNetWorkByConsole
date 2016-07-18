public class Persona {
    public Persona(String name,String apellido,Long id) {
        this.name = name;
        this.apellido=apellido;
        this.id=id;
    }

    private String name;
    private String apellido;
    private Long id;

    public String getName(){
        return name;
    }
    public String getApellido(){
        return apellido;
    }
    public Long getId(){
        return id;
    }

    public String toString() {
        return "["+id+"] "+name+" "+apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        if (!getName().equals(persona.getName())) return false;
        if (!getApellido().equals(persona.getApellido())) return false;
        return getId().equals(persona.getId());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getApellido().hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }
    //boton derecho, generar Hascode y equals
    //sirve para usar el equals en objetos
    //la clase string y int ya implementa el hashcode
}
