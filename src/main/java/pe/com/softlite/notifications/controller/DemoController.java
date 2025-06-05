package pe.com.softlite.notifications.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.softlite.dtos.DatosSalidaFail;
import pe.com.softlite.dtos.DatosSalidaOk;
import pe.com.softlite.dtos.Entrada;
import pe.com.softlite.dtos.RespuestaFail;
import pe.com.softlite.dtos.RespuestaOK;


@RestController
@RequestMapping("/api")
public class DemoController {
	
	public static final String MSJ_SUCCESS = "Validación exitosa, datos ingresados validado correctamente";
	
	
	@GetMapping("/practice")
	public ResponseEntity<String> sendNotifications() {
		try {
			return new ResponseEntity<>("prueba de servicio rest", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al ejecutar el proceso. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/validacioncuenta")
	public ResponseEntity<Object> validacionCuenta(@RequestBody Entrada datos 
			                                     , @RequestHeader (value = "Transaccion-ID") String transaccionId //, required = true
			                                     , @RequestHeader (value = "Aplicacion-ID") String applicationId
			                                     , @RequestHeader (value = "Nombre-Aplicacion") String applicationName
			                                     , @RequestHeader (value = "Usuario-Consumidor") String userConsumer
			                                     , @RequestHeader (value = "Nombre-Servicio-Consumidor") String serviceConsumerName
			                                     , @RequestHeader (value = "Ocp-Apim-Subscription-Key") String subscriptionKey) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Transaccion-ID", transaccionId);      // Puedes generar un UUID o usar un valor dinámico
        headers.add("Aplicacion-ID", applicationId);   // Nombre de tu app o servicio
        headers.add("Nombre-Aplicacion", applicationName);   // Nombre de tu app o servicio
        headers.add("Usuario-Consumidor", userConsumer);   // Nombre de tu app o servicio
        headers.add("Nombre-Servicio-Consumidor", serviceConsumerName);   // Nombre de tu app o servicio
        headers.add("Ocp-Apim-Subscription-Key", subscriptionKey);   // Nombre de tu app o servicio
        
        if(transaccionId==null || transaccionId.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(applicationId==null || applicationId.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(applicationName==null || applicationName.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(userConsumer==null || userConsumer.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(serviceConsumerName==null || serviceConsumerName.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(subscriptionKey==null || subscriptionKey.isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-003", "Error de Servidor - Por favor comunicarse con el administrador del sistema"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}
        
        
		if(datos.getDatos().getCodigoEntidad()==null || datos.getDatos().getCodigoEntidad().isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-001", "Validacion fallida - datos ingresados con validacion fallida"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}else if(datos.getDatos().getNumeroCuenta()==null || datos.getDatos().getNumeroCuenta().isEmpty()) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-001", "Validacion fallida - datos ingresados con validacion fallida"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
		}
		
		try {
			String monedaBCP13 = datos.getDatos().getNumeroCuenta().substring(10, 11);
			String monedaBCP14 = datos.getDatos().getNumeroCuenta().substring(11, 12);
			
			String bbvaPrimerosDigitos = datos.getDatos().getNumeroCuenta().substring(0, 4);
			
			String scot01 = datos.getDatos().getNumeroCuenta().substring(0, 2);
			String scot07 = datos.getDatos().getNumeroCuenta().substring(0, 2);
			String scot14 = datos.getDatos().getNumeroCuenta().substring(0, 2);
			String scot83 = datos.getDatos().getNumeroCuenta().substring(0, 2);
			
			String visaStart = datos.getDatos().getNumeroCuenta().substring(0, 1);
			String mastercardStart = datos.getDatos().getNumeroCuenta().substring(0, 1);
			String amexStart = datos.getDatos().getNumeroCuenta().substring(0, 1);
			
			System.out.println("Tipo de entidad: "+ datos.getDatos().getCodigoEntidad());
			
			if(datos.getDatos().getCodigoEntidad().equals("0002") && 
					datos.getDatos().getNumeroCuenta().length()==13 &&
					(monedaBCP13.equals("0") || monedaBCP13.equals("1")) ) { //BCP
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("0002") &&
					datos.getDatos().getNumeroCuenta().length()==14 &&
					(monedaBCP14.equals("0") || monedaBCP14.equals("1")) ) { //BCP
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("0011") && 
					datos.getDatos().getNumeroCuenta().length()==20 && 
					bbvaPrimerosDigitos.equals("0011")) { //BBVA
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("0003") && 
					datos.getDatos().getNumeroCuenta().length()==13) {
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("0009") && 
					datos.getDatos().getNumeroCuenta().length()==12 && 
					(scot01.equals("01") || scot07.equals("07") ||
							scot14.equals("14") || scot83.equals("83") ) ) {
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("visa") && 
					datos.getDatos().getNumeroCuenta().length()==16 && 
					visaStart.equals("4") && isValid(datos.getDatos().getNumeroCuenta()) ) {
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("mastercard") && 
					datos.getDatos().getNumeroCuenta().length()==16 && 
					mastercardStart.equals("5") && isValid(datos.getDatos().getNumeroCuenta())) {
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad().equals("amex") && 
					datos.getDatos().getNumeroCuenta().length()==15 && 
					amexStart.equals("3") && isValid(datos.getDatos().getNumeroCuenta())) {
				RespuestaOK respuesta = new RespuestaOK();
				respuesta.setDatos(new DatosSalidaOk("0", MSJ_SUCCESS));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.OK);
			}else if(datos.getDatos().getCodigoEntidad()==null || datos.getDatos().getCodigoEntidad().isEmpty()) {
				RespuestaFail respuesta = new RespuestaFail();
				respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-001", "Validacion fallida - datos ingresados con validacion fallida"));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
			}else if(datos.getDatos().getNumeroCuenta()==null || datos.getDatos().getNumeroCuenta().isEmpty()) {
				RespuestaFail respuesta = new RespuestaFail();
				respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-001", "Validacion fallida - datos ingresados con validacion fallida"));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.BAD_REQUEST);
			}else {
				RespuestaFail respuesta = new RespuestaFail();
				respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-000", "Validacion fallida - datos ingresados con validacion fallida"));
				return new ResponseEntity<>(respuesta, headers, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			
		} catch (Exception e) {
			RespuestaFail respuesta = new RespuestaFail();
			respuesta.setError(new DatosSalidaFail("FUNCIONAL", "FSL-FE-000", "Validacion fallida - datos ingresados con validacion fallida"));
			return new ResponseEntity<>(respuesta, headers, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private static boolean isValid(String cardNumber) {
        if (cardNumber == null || cardNumber.length() == 0) {
            return false;
        }

        int sum = 0;
        boolean doubleDigit = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char digitChar = cardNumber.charAt(i);
            if (!Character.isDigit(digitChar)) {
                return false; // No es un número válido
            }
            int digit = digitChar - '0';

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1; // Suma los dígitos si el doble es mayor que 9
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0; // La suma debe ser múltiplo de 10
    }

}
