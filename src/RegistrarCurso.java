import javax.swing.*;
import java.util.Scanner;
import java.sql.*;

public class RegistrarCurso{
    
    int ncursos;
    boolean determine=false;
    String nombre_materia="";
    int id_materia=0;
    
    Scanner read=new Scanner(System.in);

    public void ingresar_ncursos(){
    while (determine==false){
    this.ncursos= Integer.parseInt( JOptionPane.showInputDialog(null, "Ingrese el NUMERO de cursos que desea asignar"));
      if (ncursos>=1&&ncursos<=20) {
        determine=true;
      }else{
        determine=false;
        JOptionPane.showMessageDialog(null, "Por favor ingrese un numero de cursos entre 1 y 20");
      }
    }

    for (int i=0; i<ncursos; i++) {
      this.nombre_materia=JOptionPane.showInputDialog(null, "Ingrese el NOMBRE del curso #" +(i+1));
      this.id_materia=(int)(Math.random()*100);
      try {
    	  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1"); 
    	  PreparedStatement ps=conn.prepareStatement("INSERT INTO MATERIA VALUES(?, ?)");
    	  ps.setInt(1, this.id_materia);
    	  ps.setString(2, this.nombre_materia);
    	  ps.executeUpdate();
    	  ps.close();
    	  conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR DE SQL");
			e.printStackTrace();
		}
    }

    Menu menu=new Menu();
    menu.ingresar_opcion();
    menu.realizar_accion();

    }
    
    public void  mostrar_cursos(){
    System.out.println("Los cursos disponibles son");
    try {
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1"); 
        Statement stmt = conn.createStatement(); 
		ResultSet rs=stmt.executeQuery("SELECT * FROM MATERIA");
		while(rs.next()) {
			this.id_materia=rs.getInt(1);
			this.nombre_materia=rs.getString(2);
			System.out.println("ID:"+id_materia+" Nombre:"+nombre_materia);
			
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Hubo un error de SQL");
	}
  	}

}


