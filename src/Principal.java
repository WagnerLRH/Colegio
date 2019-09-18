import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("USE COLEGIO");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    Menu objeto_menu=new Menu();
    objeto_menu.ingresar_opcion();
    objeto_menu.realizar_accion();    
	}

}
