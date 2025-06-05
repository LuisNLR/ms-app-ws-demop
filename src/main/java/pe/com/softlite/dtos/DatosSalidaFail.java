package pe.com.softlite.dtos;

public class DatosSalidaFail {

	private String tipo;
	private String codigo;
	private String mensaje;
	
	public DatosSalidaFail() {
		
	}
	
	public DatosSalidaFail(String tipo, String codigo, String mensaje) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
