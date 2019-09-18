import javax.swing.*;
import java.util.Scanner;

public class Menu{

  String opcion="";
  boolean determine=false;
  Scanner read=new Scanner(System.in);

  RegistrarCurso objeto_registrar=new RegistrarCurso();
  InscribirEstudiantes objeto_iestudiantes=new InscribirEstudiantes();
  IngresarNotas objeto_inotas=new IngresarNotas();
  ConsultarEstudiante objeto_cestudiante=new ConsultarEstudiante();
  ReporteMaterias objeto_reporte=new ReporteMaterias();

  public void ingresar_opcion(){
  while (determine==false){
  opcion=JOptionPane.showInputDialog(null, "Por favor ingrese el numero de accion que desea realizar:"+
		  									"\n1. Registrar Curso"+
		  									"\n2. Inscribir Estudiantes"+
		  									"\n3. Ingresar Notas"+
		  									"\n4. Consultar Estudiante"+
		  									"\n5. Reporte General Por Materia"+
		  									"\n6. Mostrar Estudiantes Registrados"+
		  									"\n7. Salir");
  if ((opcion.equals("1"))||(opcion.equals("2"))||(opcion.equals("3"))||(opcion.equals("4"))||(opcion.equals("5"))||(opcion.equals("6"))||(opcion.equals("7"))) {
    determine=true;
  }else{
    determine=false;
    JOptionPane.showMessageDialog(null, "Por favor ingrese una opcion de la lista de arriba");
  }
  }
  }

  public void realizar_accion(){
  switch (opcion){
    case "1": objeto_registrar.ingresar_ncursos();
    break;
    case "2": objeto_registrar.mostrar_cursos();
              objeto_iestudiantes.iestu();             
    break;
    case "3": objeto_inotas.ingresar_notas();
    break;
    case "4": objeto_cestudiante.info_estu();
    break;
    case "5": objeto_reporte.reporte_general();
    break;
    case "6": objeto_iestudiantes.mostrarEstudiante();
    break;
    case "7": System.exit(0);
  }
  }

}


