package example;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;

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
  public static void main(String[] argv) {
	Object implementor = new HelloWorld ();
	String address = "http://localhost:9008/HelloWorld";
	Endpoint.publish(address, implementor);
  }




}
