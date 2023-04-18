package modelo;

import java.sql.Date;
import java.util.Objects;

public class Transaccion {
	private int id;
	private Date fecha;
	private double cantidad;
	private String categoria, tipo, descripcion;

	public Transaccion(int id, Date fecha, double cantidad, String categoria, String tipo, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	

	public Transaccion(Date fecha, double cantidad, String categoria, String tipo, String descripcion) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}



	public Transaccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", categoria=" + categoria
				+ ", tipo=" + tipo + ", descripcion=" + descripcion + "]";
	}
	
	
	

}
