package pe.com.softlite.dtos;

public class DatosSalidaOk {

	private String codigoRespuesta;
	private String mensajeRespuesta;
	
	public DatosSalidaOk() {
		
	}
	
	public DatosSalidaOk(String codigoRespuesta, String mensajeRespuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
}
