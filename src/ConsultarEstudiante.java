import java.sql.*;

import javax.swing.JOptionPane;

public class ConsultarEstudiante {
	
	int idEstudiante;
	String eNombre;
	String eApellido;
	String mNombre;
	int zona;
	int examen;
	byte determine=0;
	
	public void info_estu() {
		try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
		idEstudiante=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de carnet del estudiante que desea consultar"));
		PreparedStatement ps=conn.prepareStatement("SELECT ESTUDIANTE.NOMBRE, ESTUDIANTE.APELLIDO, MATERIA.NOMBRE, NOTA.ZONA, NOTA.EXAMEN FROM ESTUDIANTE JOIN NOTA ON ESTUDIANTE.IDESTUDIANTE=NOTA.IDESTUDIANTE JOIN MATERIA ON NOTA.IDMATERIA=MATERIA.IDMATERIA WHERE NOTA.IDESTUDIANTE=?");
		ps.setInt(1, idEstudiante);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			determine++;
			if(determine==1) {
				eNombre=rs.getString(1);
				eApellido=rs.getString(2);
				System.out.println("Estudiante: "+eNombre+" "+eApellido);
				System.out.println("Las materias asignadas son:");
			}
			mNombre=rs.getString(3);
			zona=rs.getByte(4);
			examen=rs.getByte(5);
			if((zona+examen)>=60) {
				System.out.println(mNombre+" Zona:"+zona+" Examen:"+examen);
				System.out.println("Total:"+(zona+examen)+" APROBADO");
			}else {
				System.out.println(mNombre+" Zona:"+zona+" Examen:"+examen);
				System.out.println("Total:"+(zona+examen)+" REPROBADO");
			}
		}
		rs.close();
		ps.close();
		conn.close();
		}catch(SQLException e){
			
		}
		Menu menu=new Menu();
	    menu.ingresar_opcion();
	    menu.realizar_accion();
	}
	
}
