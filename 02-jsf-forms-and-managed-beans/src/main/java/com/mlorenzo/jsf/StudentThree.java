package com.mlorenzo.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class StudentThree {
	private String firstName;
	private String lastName;
	private String country;
	
	// list of countries for the drop-down list
	List<String> countryOptions;
	
	// no args-constructor
	public StudentThree() {
		// populate the list of countries
		countryOptions = new ArrayList<>();
		
		countryOptions.add("Brazil");
		countryOptions.add("France");
		countryOptions.add("Germany");
		countryOptions.add("India");
		countryOptions.add("Turkey");
		countryOptions.add("United Kingdom");
		countryOptions.add("United States");
	}
	
	// define getter / setter methods
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getCountryOptions() {
		return countryOptions;
	}

}
