package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Date;

import conexion.ConexionBD;
import modelo.Transaccion;

public class TransaccionDAO {
	
	private ConexionBD conexion; //esto siempre se hace
		
		public TransaccionDAO() {
			this.conexion = new ConexionBD();
		}
		
		public ArrayList<Transaccion> obtenerTransacciones() { 
			// Obtenemos una conexion a la base de datos. 
			Connection con = conexion.getConexion(); 
			Statement consulta = null; //importar el que pone sql
			ResultSet resultado = null; 
			ArrayList<Transaccion> lista = new ArrayList<Transaccion>(); 
			String SQL = "SELECT * FROM transacciones ORDER by id";
			try { 
				consulta = con.createStatement();  
				resultado = consulta.executeQuery(SQL); 
				// Bucle para recorrer todas las filas que devuelve la consulta 
				while(resultado.next()) { 
					int id = resultado.getInt("id"); 
					Date fecha = resultado.getDate("fecha"); 
					
					double cantidad = resultado.getDouble("cantidad"); 
					String categoria = resultado.getString("categoria");
					String tipo = resultado.getString("tipo");
					String descripcion = resultado.getString("descripcion");
	
					Transaccion tra = new Transaccion(id, fecha, cantidad, categoria, 
							tipo, descripcion); 
					lista.add(tra); 
					} 
				} catch (SQLException e) { 
					System.out.println("Error al realizar la consulta: " +e.getMessage()); 
					} finally { 
						try { 
							resultado.close(); 
							consulta.close(); 
							conexion.desconectar(); 
							} catch (SQLException e) { 
								System.out.println("Error al liberar recursos: " +e.getMessage()); 
								} catch (Exception e) {
								} 
					}
			return lista; 
		}
		
		public Transaccion obtenerTransaccion(int id) { 
			// Obtenemos una conexion a la base de datos. 
			Connection con = conexion.getConexion(); 
			PreparedStatement consulta = null; 
			ResultSet resultado = null; 
			String SQL = "SELECT * FROM transacciones WHERE id=?";
			Transaccion tra = new Transaccion();
			try { 
				consulta = con.prepareStatement(SQL); 
				consulta.setInt(1, id); 
				resultado = consulta.executeQuery(); 
				// Sólo puede devolver una fila si la hay 
				if(resultado.next()) { 
					Date fecha = resultado.getDate("fecha"); 
					double cantidad = resultado.getDouble("cantidad"); 
					String categoria = resultado.getString("categoria");
					String tipo = resultado.getString("tipo");
					String descripcion = resultado.getString("descripcion");
	
					tra = new Transaccion(id, fecha, cantidad, categoria, 
							tipo, descripcion); } 
				} catch (SQLException e) { 
					System.out.println("Error al realizar la consulta: " +e.getMessage()); 
					} finally { 
						try { 
							resultado.close(); 
							consulta.close(); 
							conexion.desconectar(); 
							} catch (SQLException e) { 
								System.out.println("Error al liberar recursos: " +e.getMessage()); 
								} catch (Exception e) { 
									
								} 
					} 
			return tra; 
		}
		
		 public int insertarTransaccion(Transaccion transaccion) {
				// Obtenemos una conexion a la base de datos.
				ConexionBD conexion = new ConexionBD(); 
				Connection con = conexion.getConexion(); 
				PreparedStatement consulta = null; 
				String SQL = "INSERT INTO transacciones (fecha, cantidad, categoria, "
						+ "tipo, descripcion) VALUES (?,?,?,?,?)";
				int resultado=0; 
				try { 
					consulta = con.prepareStatement(SQL); 
					consulta.setDate(1, transaccion.getFecha());
					consulta.setDouble(2, transaccion.getCantidad());
					consulta.setString(3,  transaccion.getCategoria());
					consulta.setString(4,  transaccion.getTipo());
					consulta.setString(5,  transaccion.getDescripcion());
					
					resultado = consulta.executeUpdate(); 
					
				} catch (SQLException e) { 
					System.out.println("Error al realizar la inserción: " +e.getMessage()); 
					} 
				finally { 
					try { 
						consulta.close(); 
						conexion.desconectar(); 
						} 
					catch (SQLException e) { 
						System.out.println("Error al liberar recursos: " +e.getMessage()); 
					} catch (Exception e) { 
						
					} 
				} 
			return resultado; 
		}
		
		public int eliminarTransaccion (int id) { 
			// Obtenemos una conexion a la base de datos. 
			Connection con = conexion.getConexion(); 
			PreparedStatement consulta = null; 
			String SQL = "DELETE FROM transacciones WHERE id = ?";
			int resultado=0; 
			try { 
				consulta = con.prepareStatement(SQL); 
				consulta.setInt(1, id); 
				resultado = consulta.executeUpdate(); 
				if (resultado != 0) {
					System.out.println("Fila borrada.");
				}
				} catch (SQLException e) { 
					System.out.println("Error al realizar el borrado:" +e.getMessage()); 
					} finally { 
						try { 
							consulta.close(); 
							conexion.desconectar(); 
							} catch (SQLException e) {
								System.out.println("Error al liberar recursos: " +e.getMessage()); 
								} catch (Exception e) { 
									
								} 
						} 
		return resultado; 
		}
	
		public int modificarTransaccion(Transaccion transaccion) {
				// Obtenemos una conexion a la base de datos.
				ConexionBD conexion = new ConexionBD(); 
				Connection con = conexion.getConexion(); 
				PreparedStatement consulta = null; 
				String SQL = "UPDATE transacciones SET fecha = ?, cantidad = ?, categoria = ?,"
						+ "tipo = ?, descripcion = ? WHERE id = ?";
				int resultado=0; 
				try { 
					consulta = con.prepareStatement(SQL); 
					consulta.setDate(1, transaccion.getFecha()); 
					consulta.setDouble(2, transaccion.getCantidad());
					consulta.setString(3, transaccion.getCategoria());
					consulta.setString(4, transaccion.getTipo());
					consulta.setString(5, transaccion.getDescripcion());
					consulta.setInt(6, transaccion.getId());
					
					resultado = consulta.executeUpdate(); 
					if (resultado != 0) {
						System.out.println("Fila modificada.");
					}
				} catch (SQLException e) { 
					System.out.println("Error al realizar la modificación: " +e.getMessage()); 
					} 
				finally { 
					try { 
						consulta.close(); 
						conexion.desconectar(); } 
					catch (SQLException e) { 
						System.out.println("Error al liberar recursos: " +e.getMessage()); 
					} catch (Exception e) { 
						
					} 
				} 
			return resultado; 
			}
		
		public double obtenerTotal() { 
			// Obtenemos una conexion a la base de datos. 
						Connection con = conexion.getConexion(); 
						Statement consulta = null; //importar el que pone sql
						ResultSet resultado = null;
						double total = 0;
						ArrayList<Transaccion> lista = new ArrayList<Transaccion>(); 
						String SQL = "SELECT * FROM transacciones";
						try { 
							consulta = con.createStatement();  
							resultado = consulta.executeQuery(SQL); 
							// Bucle para recorrer todas las filas que devuelve la consulta 
							while(resultado.next()) { 
								double cantidad = resultado.getDouble("cantidad"); 
								total = total + cantidad;
								
								} 
							} catch (SQLException e) { 
								System.out.println("Error al realizar la consulta: " +e.getMessage()); 
								} finally { 
									try { 
										resultado.close(); 
										consulta.close(); 
										conexion.desconectar(); 
										} catch (SQLException e) { 
											System.out.println("Error al liberar recursos: " +e.getMessage()); 
											} catch (Exception e) {
											} 
								}
				return total; 
					
		}
		
}
