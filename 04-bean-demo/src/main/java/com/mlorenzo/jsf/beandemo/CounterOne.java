package com.mlorenzo.jsf.beandemo;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
// Anotación que establece el scope o ámbito del bean de JSF en Application. En este caso, solo se crea un bean de JSF para toda la aplicación.
// Este bean es compartido por todos los usuarios o clientes de la aplicación (por ejemplo, navegadores web).
// Nota: Si no se indica esta anotación, el scope o ámbito por defecto de un bean de JSF es Request.
@ApplicationScoped
public class CounterOne {
	private int value = 0;
	
	// Incrementa el valor del contado y redirige a la vista "02-counter-one.xhtml"
	public String increment() {
		value++;
		
		// JSF añadirá de forma automática la extensión "xhtml" al nombre de la vista para localizar el archivo.
		return "02-counter-one";
	}

	// define getter / setter methods
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
