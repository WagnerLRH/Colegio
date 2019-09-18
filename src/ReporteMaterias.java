import java.sql.*;
import javax.swing.JOptionPane;

public class ReporteMaterias {

	RegistrarCurso objeto_curso=new RegistrarCurso();

	int idcurso;
	int total;
	int promedio;
	int aprobados;
	int reprobados;

	public void reporte_general() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?useSSL=false", "root", "Puntitos1");
			objeto_curso.mostrar_cursos();
			idcurso=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del curso cuyos datos quiere obtener"));
			PreparedStatement ps=conn.prepareStatement("SELECT COUNT(IDESTUDIANTE) AS TALUMNOS, AVG(ZONA+EXAMEN) AS PROMEDIO FROM NOTA WHERE IDMATERIA=?");
			ps.setInt(1, idcurso);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				total=rs.getInt(1);
				promedio=rs.getInt(2);
				System.out.println("Hay un total de "+total+" estudiantes asignados a este curso");
				System.out.println("La nota promedio es "+promedio);
			}
			ps=conn.prepareStatement("SELECT COUNT(IDESTUDIANTE) AS TOTALAPROBADOS FROM NOTA WHERE IDMATERIA=? AND ZONA+EXAMEN>=60");
			ps.setInt(1, idcurso);
			rs=ps.executeQuery();
			while(rs.next()) {
				aprobados=rs.getInt(1);
				System.out.println("El total de estudiantes que aprobaron es "+aprobados);
			}
			ps=conn.prepareStatement("SELECT COUNT(IDESTUDIANTE) AS TOTALREPROBADOS FROM NOTA WHERE IDMATERIA=? AND ZONA+EXAMEN<60");
			ps.setInt(1, idcurso);
			rs=ps.executeQuery();
			while(rs.next()) {
				reprobados=rs.getInt(1);
				System.out.println("El total de estudiantes que reprobaron es "+reprobados);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Menu menu=new Menu();
	    menu.ingresar_opcion();
	    menu.realizar_accion();
	}

}


