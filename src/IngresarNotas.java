import java.sql.*;
import javax.swing.*;

public class IngresarNotas{

  String materia;
  int idmateria;
  int scarnet=0;
  int carnet=0;
  int zona=0;
  int examen=0;
  int nestudiantes=0;
  int ncursos=0; 

  public void ingresar_notas(){
    try {
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
    	scarnet=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de carnet del estudiante cuyas notas quiere ingresar"));
        PreparedStatement ps=conn.prepareStatement("SELECT MATERIA.IDMATERIA, MATERIA.NOMBRE FROM MATERIA JOIN NOTA ON MATERIA.IDMATERIA=NOTA.IDMATERIA WHERE NOTA.IDESTUDIANTE=?");
        ps.setInt(1, scarnet);
        ResultSet rs=ps.executeQuery();
        System.out.println("Este estudiante tiene las siguientes materias asignadas:");
        while(rs.next()) {
    		idmateria=rs.getInt(1);
    		materia=rs.getString(2);
    		System.out.println("ID:"+idmateria+" Nombre:"+materia);
    	}
        rs.close();
        ps.close();
        conn.close();
    } catch(SQLException e) {
    	System.out.println("ERROR DE SQL");
		e.printStackTrace();
    }

    try {
        idmateria=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la materia cuyas notas quiere ingresar"));
        zona=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la ZONA del estudiante"));
        examen=Integer.parseInt(JOptionPane.showInputDialog("Finalmente ingrese la nota del EXAMEN final del estudiante"));
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
        PreparedStatement ps=conn.prepareStatement("UPDATE NOTA SET ZONA=?, EXAMEN=? WHERE IDESTUDIANTE=? AND IDMATERIA=?");
        ps.setByte(1, (byte)zona);
        ps.setByte(2, (byte)examen);
        ps.setInt(3, scarnet);
        ps.setInt(4, idmateria);
        ps.executeUpdate();
        System.out.println("Las notas se han ingresado de manera correcta");
        ps.close();
        conn.close();      
    } catch(SQLException e) {
    	System.out.println("ERROR DE SQL");
		e.printStackTrace();
    }
    Menu menu=new Menu();
    menu.ingresar_opcion();
    menu.realizar_accion();
  }

}
