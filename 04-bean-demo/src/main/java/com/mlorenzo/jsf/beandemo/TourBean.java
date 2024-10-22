package com.mlorenzo.jsf.beandemo;

import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class TourBean {
	private String kindOfTour;
	
	// Navegación condicional
	// Este método, en función del valor de la propiedad "kindOfTour", redirigirá a una vista o a otra.
	public String startTheTour() {
		if(kindOfTour != null  && kindOfTour.equals("city")) {
			// Devolvemos el nombre de la vista.
			// JSF añadirá la extensión ".xhtml" al nombre de la vista de forma automática para localizar el archivo.
			return "01-city-tour";
		}
		
		return "01-country-tour";
	}

	// define getter / setter methods
	
	public String getKindOfTour() {
		return kindOfTour;
	}

	public void setKindOfTour(String kindOfTour) {
		this.kindOfTour = kindOfTour;
	}
	
}
