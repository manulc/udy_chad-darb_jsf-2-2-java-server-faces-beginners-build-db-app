package com.mlorenzo.jsf.beandemo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
// Anotación que establece el scope o ámbito del bean de JSF en @RequestScoped. En este caso, se crea un nuevo bean de JSF por cada nueva petición http (vida corta del bean).
// Es útíl y se suele utilizar para enviar datos desde un formulario (un bean nuevo por cada envío o petición http).
// Nota: Si no se indica esta anotación, el scope o ámbito por defecto de un bean de JSF es Request.
@RequestScoped
public class CounterThree {
	private int value = 0;
	
	// Incrementa el valor del contado y redirige a la vista "02-counter-three.xhtml"
	public String increment() {
		value++;
		
		// JSF añadirá de forma automática la extensión "xhtml" al nombre de la vista para localizar el archivo.
		return "02-counter-three";
	}

	// define getter / setter methods
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
