package practica2_2;


public class Agenda{
    private String name;
    private String phone;
    private String location;

    //inicio getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    //fin getters y setters

    /**
     * Descripcion: Esta función imprime los atributos de las instancias de Agenda
     * @param ninguno
     * @return name phone location
     */
    public void output() {
        System.out.println("nombre: " + name + " telefono: " + phone + " ubicacion: " + location);
    }

    //constructor

    public Agenda(String name, String phone, String location) {
        this.name = name;
        this.phone = phone;
        this.location = location;
    }
}