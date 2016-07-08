package example;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Iterator;

import static example.Utilidades.*;

/**
 * Created by manuel on 07/07/16.
 */
@WebService()
public class HelloWorld {
  static ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
  static ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
  @WebMethod
  public int crearEstudiante(String nombre, int matricula, String carrera, String[] asignaturas) {

	Estudiante e = new Estudiante(nombre, matricula,carrera);
    ArrayList<Asignatura> asignaturasEstudiante = new ArrayList<>();
	if(estudianteExiste(matricula) )
	  return -1;
    for(String a: asignaturas){
        System.out.print(a);
        if(!asignaturaExiste(a))
            return -2;

        asignaturasEstudiante.add(buscarAsignatura(a));
    }

    e.setAsignaturas(asignaturasEstudiante);
	estudiantes.add(e);
	printEstudiantes();
	return e.getMatricula();
  }

    @WebMethod
    public String editarEstudiante(String nombre, int matricula, String carrera) {

        Estudiante e = buscarEstudiante(matricula);

        if(!estudianteExiste(matricula) )
            return "error";

        e.setNombre(nombre);
        e.setCarrera(carrera);


        printEstudiantes();
        return "ok";
    }

  @WebMethod
  public String crearAsignatura(String codigo,  String nombre) {

	Asignatura a = new Asignatura(codigo, nombre);
	if(asignaturaExiste(codigo))
	  return "Error";
	asignaturas.add(a);
	printEstudiantes();
	printAsignaturas();
	return a.getCodigo();
  }
  @WebMethod
  public Estudiante getEstudiante(String nombre, int matricula, String carrera) {

	Estudiante e = new Estudiante(nombre, matricula,carrera);


	return e;
  }

    @WebMethod
    public String borrarAsignaturaEstudiante(int matricula, String codigoAsignatura) {

        if(!estudianteExiste(matricula))
            return "error_matricula";
        if(!asignaturaExiste(codigoAsignatura))
            return "error_asignatura";
        Estudiante e = buscarEstudiante(matricula);
        Iterator<Asignatura> it = e.getAsignaturas().iterator();
        boolean borradoExitoso = false;
        while (it.hasNext()) {
            if (it.next().getCodigo().equals(codigoAsignatura)){
                it.remove();
                borradoExitoso = true;
                break;
            }
        }
        printEstudiantes();
        if(borradoExitoso)
            return "ok";
        else
            return "error_mas";


    }

    @WebMethod
    public String agregarAsignaturaEstudiante(int matricula, String codigoAsignatura) {

        if(!estudianteExiste(matricula))
            return "error_matricula";
        if(!asignaturaExiste(codigoAsignatura))
            return "error_asignatura";
        Estudiante e = buscarEstudiante(matricula);
        if(asignaturaExisteEstudiante(codigoAsignatura,e))
            return "error_asignatura_repetida";
        e.getAsignaturas().add(buscarAsignatura(codigoAsignatura));
        printEstudiantes();
        return "ok";

    }
  public static void main(String[] argv) {
	Object implementor = new HelloWorld ();
	String address = "http://localhost:9011/HelloWorld";
	Endpoint.publish(address, implementor);
  }




}
