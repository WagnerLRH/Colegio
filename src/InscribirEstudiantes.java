import java.sql.*;
import javax.swing.*;

public class InscribirEstudiantes{
	
	String nombre;
    String apellido;
    int carnet;
    int idcurso;
    int ncursos;
    
    public Integer [] materias;
	
    public static int nestudiantes;
    
    public void iestu(){
    nestudiantes=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el NUMERO de estudiantes a registrar"));

    for (int i=0; i<nestudiantes; i++) {
      nombre=JOptionPane.showInputDialog("Ingrese el NOMBRE del estudiante #" +(i+1));
      apellido=JOptionPane.showInputDialog("Ingrese el APELLIDO del estudiante #" +(i+1));
      carnet=(int)(Math.random()*10000);     
      ncursos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese CUANTOS cursos tendra asignados este estudiante"));
      materias=new Integer [ncursos];
      for (int j=0; j<ncursos; j++) {
    	  idcurso=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del curso#"+(j+1)+" que desea asignarle a este estudiante"));  
    	  materias[j]=new Integer(idcurso);
      }
      try {
    	  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
    	  PreparedStatement ps=conn.prepareStatement("INSERT INTO ESTUDIANTE VALUES(?, ?, ?)");
    	  ps.setInt(1, carnet);
    	  ps.setString(2, nombre);
    	  ps.setString(3, apellido);
    	  ps.executeUpdate();
    	  ps.close();
    	  conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR DE SQL");
			e.printStackTrace();
		}
      
      for (int k=0; k<ncursos; k++) {
    	  try {
    		  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
        	  PreparedStatement ps=conn.prepareStatement("INSERT INTO ESTUDIANTE VALUES(?, ?, ?)");
        	  ps=conn.prepareStatement("INSERT INTO NOTA VALUES(?, ?, ?, ?, ?)");
        	  ps.setInt(1, (int)(Math.random()*100000));
        	  ps.setByte(2, (byte) 0);
        	  ps.setByte(3, (byte) 0);
        	  ps.setInt(4, carnet);
        	  ps.setInt(5, materias[k]);
        	  ps.executeUpdate();
        	  ps.close();
        	  conn.close();
    		} catch(SQLException e) {
    			System.out.println("ERROR DE SQL");
    			e.printStackTrace();
    		}
      }      
    }
    Menu menu=new Menu();
    menu.ingresar_opcion();
    menu.realizar_accion();
  }
    
    public void mostrarEstudiante() {
    	try {
    		int idestudiante;
    		String nombre;
    		String apellido;
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
    		Statement stmt=conn.createStatement();
    		ResultSet rs=stmt.executeQuery("SELECT * FROM ESTUDIANTE");
    		System.out.println("Los estudiantes actualmente inscritos son:");
    		while(rs.next()) {
    			idestudiante=rs.getInt(1);
    			nombre=rs.getString(2);
    			apellido=rs.getString(3);
    			System.out.print(idestudiante+" ");
    			System.out.print(nombre+" ");
    			System.out.print(apellido);
    			System.out.println();
    		}
    		rs.close();
    		stmt.close();
    		conn.close();
    	} catch(SQLException e) {
    		System.out.println("Error de SQL");
    		e.printStackTrace();
    	}
    	Menu menu=new Menu();
    	menu.ingresar_opcion();
    	menu.realizar_accion();
    }
    
}
