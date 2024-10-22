package com.mlorenzo.jsf.beandemo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
// Anotación que establece el scope o ámbito del bean de JSF en Session. En este caso, se crea un nuevo bean de JSF por cada nueva sesión.
// Es útíl y se suele utilizar cuando se quiere hacer un seguimiento de las acciones de cada usuario o cliente de la aplicación (por ejemplo, un navegador web) por separado.
// Nota: Si no se indica esta anotación, el scope o ámbito por defecto de un bean de JSF es Request.
@SessionScoped
public class CounterTwo {
	private int value = 0;
	
	// Incrementa el valor del contado y redirige a la vista "02-counter-two.xhtml"
	public String increment() {
		value++;
		
		// JSF añadirá de forma automática la extensión "xhtml" al nombre de la vista para localizar el archivo.
		return "02-counter-two";
	}

	// define getter / setter methods
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
