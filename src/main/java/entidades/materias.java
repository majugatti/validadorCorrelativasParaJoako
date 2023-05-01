/*
/@majugatti
*/

package entidades;

/**
 *
 * @author majugatti
 */
public class materias {
    
    private String nombre;
    private int idMateria;
    private String correlativas;

    public materias() {
    }

    public materias(String nombre, int idMateria, String correlativas) {
        this.nombre = nombre;
        this.idMateria = idMateria;
        this.correlativas = correlativas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(String correlativas) {
        this.correlativas = correlativas;
    }

    @Override
    public String toString() {
        return "Materias=\n {"+ "Nombre:" + nombre + ", IdMateria: " + idMateria + ".\n Todas sus correlativas: " + correlativas + '}';
    }
    
    
    

}