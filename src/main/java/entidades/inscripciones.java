/*
@majugatti
*/

package entidades;

import java.time.LocalDate;

/**
 *
 * @author majugatti
 */
public class inscripciones {
    
    private String alumnoInscribe;
    private String materiaInscribe;
    private LocalDate fechaInscribe;

    public inscripciones() {
    }

    public inscripciones(String alumnoInscribe, String materiaInscribe, LocalDate fechaInscribe) {
        this.alumnoInscribe = alumnoInscribe;
        this.materiaInscribe = materiaInscribe;
        this.fechaInscribe = fechaInscribe;
    }

    public String getAlumnoInscribe() {
        return alumnoInscribe;
    }

    public void setAlumnoInscribe(String alumnoInscribe) {
        this.alumnoInscribe = alumnoInscribe;
    }

    public String getMateriaInscribe() {
        return materiaInscribe;
    }

    public void setMateriaInscribe(String materiaInscribe) {
        this.materiaInscribe = materiaInscribe;
    }

    public LocalDate getFechaInscribe() {
        return fechaInscribe;
    }

    public void setFechaInscribe(LocalDate fechaInscribe) {
        this.fechaInscribe = fechaInscribe;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "DNI del alumno que se Inscribe=" + alumnoInscribe + ", materiaInscribe=" + materiaInscribe + ", fechaInscribe=" + fechaInscribe + '}';
    }
    
   
}

