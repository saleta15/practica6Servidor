package example;

import java.util.Iterator;

/**
 * Created by manuel on 07/07/16.
 */
public class Utilidades {

    public static void printEstudiantes(){

        for(Estudiante e: HelloWorld.estudiantes){
            System.out.println("Nombre: "+e.getNombre()+" Carrera: "+e.getCarrera()+ " Matricula: " + e.getMatricula());
            for(Asignatura a : e.getAsignaturas()){
                System.out.println("\tCodigo asignatura: "+  a.getCodigo());
            }
        }
        System.out.println("----------------------------------------");
    }

    public static void printAsignaturas(){

        for(Asignatura a: HelloWorld.asignaturas){
            System.out.println("Nombre: "+a.getNombre()+" Codigo: "+a.getCodigo());
        }
        System.out.println("----------------------------------------");
    }
    public static boolean asignaturaExiste(String codigo){
        for(Asignatura a: HelloWorld.asignaturas){
            if(a.getCodigo().equals(codigo))
                return true;
        }
        return false;
    }

    public static boolean estudianteExiste(int matricula){
        for(Estudiante e: HelloWorld.estudiantes){
            if(e.getMatricula() == (matricula))
                return true;
        }
        return false;
    }

    public static Asignatura buscarAsignatura(String codigo){

        for(Asignatura a: HelloWorld.asignaturas){
            if(a.getCodigo().equals(codigo))
                return a;
        }
        return null;
    }

    public static Estudiante buscarEstudiante(int matricula){

        for(Estudiante e: HelloWorld.estudiantes){
            if(e.getMatricula() == (matricula))
                return e;
        }
        return null;
    }

    public static boolean asignaturaExisteEstudiante(String codigo, Estudiante e){
        for(Asignatura a: e.getAsignaturas()){
            if(a.getCodigo().equals(codigo))
                return true;
        }
        return false;
    }


}
