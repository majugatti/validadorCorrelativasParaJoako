/*
@majugatti
 */
package funciones;

import entidades.alumnos;
import entidades.inscripciones;
import entidades.materias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author majugatti
 */
public class menu {
    
       
    
    public static void main (String [] args) throws SQLException{
        Scanner sc = new Scanner (System.in);
        String opcionMenu = sc.next();
    
       //planteo el menu 
       
       //me queda pendiente hacer una funcion para anotar una materia aprobada a un alumno ya existente
         
       do{
         
            System.out.println("Menu de opciones de Maju. Si quiere terminar escriba Tarugo");
            System.out.println("Presione 1 para ver el listado de alumnos");
            System.out.println("Presione 2 para ver el listado de materias");
            System.out.println("Presione 3 para agregar un alumno");
            System.out.println("Presione 4 para agregar una materia");
            System.out.println("Presione 5 para anotar a un alumno en una materia");
            
            
            switch (opcionMenu){
            case ("1"):
                {
                    System.out.println("Ingreso ver listado de alumnos:");
                    imprimoAlumnos();
                }
                break;
            case ("2"):
                {  
                    System.out.println("Ingreso ver listado de materias:");
                    imprimoMaterias();
                }    
                break;
            case ("3"):
                {
                    System.out.println("Ingreso agregar un alumno");
                    agregoAlumnos();
                }
                break;
            case ("4"):
            {
                System.out.println("Ingreso agregar una materia");
                agregoMateria();
            }
                break;
            case ("5"):
            {    
                System.out.println("Ingreso anotar a un alumno en una materia");
                anotarAlumnoEnMateria();
            }    
                break;
            default:
                System.out.println("ERROR: Ingrese 1 2 3 4 5 o Tarugo");
        
            }//del switch
        }while (!"Tarugo".equals(sc.next()));
                
    } //del main

     
    public static void imprimoAlumnos(){
    
        //esta funcion hace:
        //1. Imprime por consola el listado de todos los alumnos anotados en la BD
        //Con todos sus datos
        
       conexion miconexion = new conexion ();
        
       ResultSet rs;//contenedor. Genera un apuntador a la BD donde se encuentra la consulta
       ResultSetMetaData mtd;//datos que describen otros datos
       
       alumnos alumno = new alumnos();
       String error=null;
              
       String consulta= "SELECT * FROM alumno.alumno";
     
       try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement st = (Statement) miconexion.estableceConexion();
                
                
                rs = st.executeQuery(consulta);
                mtd = rs.getMetaData();
                
                //ahora hago un recorrido por la tabla alumnos.
                //rs es un apuntador al 1er registro de la tabla, despues al 2do y asi
                //hasta que no haya mas filas en la tabla 
                
                
                while (rs.next()){
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setDni(rs.getString("dni"));
                    alumno.setLegajo(rs.getInt("legajo"));
                    alumno.setMateriasAprobadas(rs.getString("materias_aprobadas"));
                    
                    //ahora que tengo mi alumno prolijo, lo imprimo por consola.
                    
                    System.out.println(alumno.toString());
                
                }//cuando salgo del while ya imprimi todos mis alumnos
               
            }catch (SQLException e){
                    error = e.getMessage();
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    
    public static void imprimoMaterias(){
        //esta funcion hace:
        //1. Imprime el listado de materias que tiene la BD.
        //junto con sus correlativas.
        
       conexion miconexion = new conexion ();
        
       ResultSet rs;//contenedor. Genera un apuntador a la BD donde se encuentra la consulta
       ResultSetMetaData mtd;//datos que describen otros datos
       
       materias materia = new materias();
       String error;
              
       String consulta= "SELECT * FROM alumno.materia";
     
       try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement st = (Statement) miconexion.estableceConexion();
                
                
                rs = st.executeQuery(consulta);
                mtd = rs.getMetaData();
                
                //ahora hago un recorrido por la tabla alumnos.
                //rs es un apuntador al 1er registro de la tabla, despues al 2do y asi
                //hasta que no haya mas filas en la tabla 
                
                
                while (rs.next()){
                   materia.setIdMateria(rs.getInt("id_materia"));
                   materia.setNombre(rs.getString("nombre"));
                   materia.setCorrelativas(rs.getString("correlativas"));
                    //ahora que tengo mi alumno prolijo, lo imprimo por consola.
                    
                    System.out.println(materia.toString());
                
                }//cuando salgo del while ya imprimi todas las materias
               
            }catch (SQLException e){
                    error = e.getMessage();
                    System.out.println(error);
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
       
        
        
    }
    
    public static void agregoAlumnos() throws SQLException{
    
        //esta funcion hace:
        //1. Solicita por teclado los datos de un alumno
        //2. Busca si en la BD esta el alumno a cargar.
        //si no esta lo agrega.
        
        alumnos alumno = new alumnos();
        Scanner sc = new Scanner (System.in);
        
        System.out.println("A continuacion, ingrese los datos de un alumno nuevo:");
        
        String opcionMenu; 
        do{
            System.out.println("Ingrese nombre y presione enter. Solo caracteres no numeros");
            opcionMenu = sc.next();
        }while (! (validoPalabra (opcionMenu)));
        //aca seguro mi nombre tiene formato letra.
        alumno.setNombre(opcionMenu);

        do{
           System.out.println("Ingrese apellido y presione enter. Solo caracteres no numeros");
           opcionMenu = sc.next();
        }while (!(validoPalabra(opcionMenu)));
        //aca seguro mi apellido tiene formato letra.
        alumno.setApellido(opcionMenu);

        do{
            System.out.println("Ingrese DNI y presione enter. Recuerde son 8 digitos numericos");
            opcionMenu = sc.next();
        }while (! (validaDni (opcionMenu)));
        //aca seguro mi dni tiene 8 numeros
        alumno.setDni(opcionMenu);
        
        //el legajo no lo pido por teclado. Deberia ser autocompletable por la base de datos.
        //asumo que no tiene materias aprobadas porque es un alumno nuevo.
        //ahora que tengo mis datos por consola, los agrego a mi base de datos.
        
       //no compruebo si el dni ya existe porque para eso puse como UNIQUE
       //la fila DNI en la base de datos.
       //Si ya existe me tira una excepcion y el programa sigue
       
       String consulta =  "INSERT INTO `alumno´.`alumno´ (`nombre´, `apellido´, `dni´) VALUES (?,?)";
       
         try{
            PreparedStatement ps;
            conexion miconexion = new conexion ();
            ps = miconexion.estableceConexion().prepareStatement(consulta);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getDni());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Hubo un error al cargar al alumno: " + e);
        }
       
        
            
    }//de la funcion agrego alumnos
   
    
   
    
    public static boolean validaDni (String dni){
        return dni.matches("<[0-9](8)$");
    
    }
    
    public static boolean validoPalabra (String palabra){
        return palabra.matches("<[a-z][A-Z]$");
    }
            
    
    public static void agregoMateria(){
        //esta funcion hace:
        //1. Solicita por teclado los datos de la materia a agregar.
        //2. Busca en la BD si la materia ya esta.
        //3. Si no esta la agrega
    
        materias materia = new materias();
        Scanner sc = new Scanner (System.in);
        
        System.out.println("A continuacion, ingrese los datos de una materia nueva:");
        
        //El nombre de la materia puede tener nros.
        String opcionMenu; 
        System.out.println("Ingrese nombre y presione enter");
        opcionMenu = sc.next();
        materia.setNombre(opcionMenu);

        System.out.print ("Ingrese correlativas. Las mismas tienen que estar separadas por coma.");
        System.out.println("Una vez que presione enter ya no va a poder agregar otra correlativa");
        opcionMenu = sc.next();
        materia.setCorrelativas(opcionMenu);

       //ahora que tengo mis datos por consola, los agrego a mi base de datos.
        
        conexion miconexion = new conexion ();
         
        PreparedStatement ps;
        
        String consulta = "INSERT INTO `alumno´.`materias´ (`nombre´, `correlativas´) VALUES (?,?)";
        try{
           ps = miconexion.estableceConexion().prepareStatement(consulta);
           ps.setString(2, materia.getNombre());
           ps.setString(3, materia.getCorrelativas());
           ps.executeUpdate();
        } catch (SQLException e){
                    System.out.println(" Hubo un error al cargar la materia: " + e);
        }
       
           
        
        
    }
    
    public static void anotarAlumnoEnMateria(){
        
        //esta funcion hace:
        //1. Solicita los datos por teclado de un alumno. SOLO EL DNI.
        //2. Solicita los datos por teclado de una materia a la que se quiere anotar.

        //3. Consulta si el alumno existe. Si no existe termina.
        //4. Consulta si la materia a anotarse existe. Si no existe termina.
        
        //5. Obtiene las correlativas de la materia. Si no tiene termina.
        //6. Si tiene correlativas obtiene el string
        //7. Compara ese string con el string de materiasaprobadas del alumno.
        //8. Si no contiene imprime: se anota. Si no lo contiene imprime: no se anota.
        
        //9. Guarda el resultado de la inscripcion en la BD 
       
        //1.
        Scanner sc = new Scanner (System.in);
        alumnos alumno = new alumnos ();
        materias materia = new materias();
        inscripciones inscripcion = new inscripciones();
        String aux=null;
        
        do{
            System.out.println("Ingrese el dni del alumno a anotar. Recuerde son 8 digitos numericos");
            aux = sc.next();
        }while (! (validaDni (aux)));
        //aca seguro mi dni tiene 8 numeros
        alumno.setDni(aux);
        
        
        //2. 
        System.out.println("Ingrese el nombre de la materia a la que se quiere anotar");
        materia.setNombre(sc.next());
        
        //3.
        String where = "WHERE (`dni´ =" + aux + ");";
        String consulta = "SELECT  FROM `alumno´.`alumno´" + where;
       
        int cont = 0;
        
        String aux5 =null;
        String aux6= null;
        try{
            PreparedStatement ps;
            ResultSet rs;
            conexion miconexion = new conexion ();
            ps = miconexion.estableceConexion().prepareStatement(consulta);
            rs = ps.executeQuery(consulta);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            String aprobadasDelAlumno = rs.getString("materias_aprobadas");
            aux5 = aprobadasDelAlumno;
            
            //en aprobadasDelAlumno tengo todo mi alumno, solo preciso la parte de materiasAprobadas
            
            if (rs.wasNull()){
                System.out.println("El alumno que quiere agregar no existe.");
                cont++;
            }
            
        } catch (SQLException e){
            System.out.println("Hubo un error al cargar al alumno: " + e);
        }
        
        //4. Si el alumno existe sigue con la materia
        String aux2 = materia.getNombre();
        String aux7 = null;
             
        if (cont == 0){
             String where2 = "WHERE (`nombre´ =" + aux2 + ");";
             String consulta2 = "SELECT * FROM `alumno´.`materia´" + where2;
            
            try{
            PreparedStatement ps;
            ResultSet rs;
            conexion miconexion = new conexion ();
            ps = miconexion.estableceConexion().prepareStatement(consulta2);
            rs = ps.executeQuery(consulta2);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            String materAprobAlum = rs.getString("correlativas");
            aux7= materAprobAlum;
            
            if (rs.wasNull()){
                System.out.println("La materia a la cual se quiere anotar no existe.");
                cont++;
            }
            
            
        } catch (SQLException e){
            System.out.println("Hubo un error al cargar la materia " + e);
        }
        }//del if
        
        //o no hace mas nada porque no encontro la materia ni el alumno
        //o si es 0 es porque existe el alumno y la materia.
        
        //5.
        int cont2=0; 
        if (cont == 0){
             String where3 = "WHERE (`nombre´ =" + aux2 + ");";
             String consulta3 = "SELECT `correlativas´ FROM `alumno´.`materia´" + where3;
             
            
            try{
            PreparedStatement ps;
            ResultSet rs;
            conexion miconexion = new conexion ();
            ps = miconexion.estableceConexion().prepareStatement(consulta3);
            rs = ps.executeQuery(consulta3);
            //aca ya tengo en mi rs lo todas las materias correlativas
            
                if (rs.wasNull()){
                  System.out.println("No existen materias correlativas!!");
                  System.out.print("El alumno" + alumno.getDni() + "se puede anotar en la materia: ");
                  System.out.println(aux2);
                  cont2++;
                
                }
            ResultSetMetaData rsmd = rs.getMetaData();
           //en correlativasDelaMateria voy tener todas mis correlativas del alumno
            
            String aprobadasDelAlumno = rs.getString("materias_aprobadas");
            aux5 = aprobadasDelAlumno;
            
           
           //ahora tengo correlativasDelaMateria
           //aprobadasDelAlumno
                
                
            } catch (SQLException e){
                System.out.println("Hubo un error al intentar levantar las materias correlativas " + e);
            }//del catch
            
    }//if cont==0 entonces existe la materia y el alumno a agregar
        
    if ((cont ==0)){
         
        if ((cont2 ==1)){
            //lo anoto directo porque no tiene correlativas la materia
            System.out.print("Agregue a la BD Inscripcion al alumno");
            
            inscripcion.setAlumnoInscribe(aux);//el dni del alumno
            inscripcion.setFechaInscribe(LocalDate.now()); //la fecha
            inscripcion.setMateriaInscribe(aux2);//la materia a la que se anota
            
            String consulta4 = "INSERT INTO `alumno´.`inscripcion´ (`dni_alumno´, `estado_inscripcion´,`fecha_inscripcion´, `materia_inscripcion´, ) VALUES (?,?,?,?)";
            try{
                PreparedStatement ps;
                ResultSet rs;
                conexion miconexion = new conexion ();
                ps = miconexion.estableceConexion().prepareStatement(consulta4);
                ps.setString(2, inscripcion.getAlumnoInscribe());
                ps.setString(3, "APROBADO");
                ps.setString(5, aux2);
                
                ps.executeUpdate();
        } catch (SQLException e){
                    System.out.println("Error cargando la inscripcion: "+ e);
        }
        }else{
            //compruebo si tiene todas las correlativas el alumno
            if (aux5.contains(aux7)){
                System.out.println("Tiene todas las correlativas necesarias. Se inscribe como APROBADO");
                inscripcion.setAlumnoInscribe(aux);//el dni del alumno
                inscripcion.setFechaInscribe(LocalDate.now()); //la fecha
                inscripcion.setMateriaInscribe(aux2);//la materia a la que se anota
            
            String consulta4 = "INSERT INTO `alumno´.`inscripcion´ (`dni_alumno´, `estado_inscripcion´,`fecha_inscripcion´, `materia_inscripcion´, ) VALUES (?,?,?,?)";
            try{
                PreparedStatement ps;
                ResultSet rs;
                conexion miconexion = new conexion ();
                ps = miconexion.estableceConexion().prepareStatement(consulta4);
                ps.setString(2, inscripcion.getAlumnoInscribe());
                ps.setString(3, "APROBADO");
                ps.setString(5, aux2);
                
                ps.executeUpdate();
            } catch (SQLException e){
                    System.out.println("Error cargando la inscripcion: "+ e);
        }   
            
            
        }else{
                System.out.println("No tiene las correlativas necesarias. Se incribe como DESAPROBADO");
                inscripcion.setAlumnoInscribe(aux);//el dni del alumno
                inscripcion.setFechaInscribe(LocalDate.now()); //la fecha
                inscripcion.setMateriaInscribe(aux2);//la materia a la que se anota
            
                String consulta4 = "INSERT INTO `alumno´.`inscripcion´ (`dni_alumno´, `estado_inscripcion´,`fecha_inscripcion´, `materia_inscripcion´, ) VALUES (?,?,?,?)";
                try{
                    PreparedStatement ps;
                   ResultSet rs;
                   conexion miconexion = new conexion ();
                   ps = miconexion.estableceConexion().prepareStatement(consulta4);
                   ps.setString(2, inscripcion.getAlumnoInscribe());
                   ps.setString(3, "DESAPROBADO");
                   ps.setString(5, aux2);
                
                    ps.executeUpdate();
        } catch (SQLException e){
                    System.out.println("Error cargando la inscripcion: "+ e);
        }
            
            }
            
        }
       
        
    }//el if
        
        
            
        
       
        
        
}//de anotar alumno en materia.
   
    
   
    
      
       
      
    
}//de la clase menu


