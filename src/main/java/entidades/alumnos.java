/*
@majugatti
*/
package entidades;

public class alumnos {
 
    private String nombre;
    private String apellido;
    private String dni;
    private int legajo;
    private String materiasAprobadas;

    public alumnos() {
    }

    public alumnos(String nombre, String apellido, String dni, int legajo, String materiasAprobadas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(String materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    @Override
    public String toString() {
        return "Alumnos=\n {"+ "Nombre:" + nombre + ", Apellido:" + apellido + ", DNI:" + dni + ", Legajo:" + legajo + ".\n Todas sus Materias Aprobadas: " + materiasAprobadas + '}';
    }



    
    
}


