package conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Andrés Pino Gallardo
 * 
 * COnexión con la base datos
 *
 */
public class ConexionBD {

	private static final String database = "controlGastos";
	private static final String usuario = "root";
	private static final String contrasena = "";
	private static final String url="jdbc:mysql://localhost/"+database;
	
	private Connection conexion=null;
	
	
	public Connection getConexion() {
		if (conexion!=null) {
			return conexion;
		}
		
		// REgistra el driver de MySQL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexion a "+database+" correcta");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no registrado");
		} catch (SQLException e) {
			System.out.println("Error SQLException: "+e.getMessage());
		}
		return conexion;
	}
	
	public void desconectar() {
		try {
			conexion.close();
			conexion=null;
		} catch (SQLException e) {
			System.out.println("Erorr cerrrando la conexion "+ e.getMessage());
		}
	}

}