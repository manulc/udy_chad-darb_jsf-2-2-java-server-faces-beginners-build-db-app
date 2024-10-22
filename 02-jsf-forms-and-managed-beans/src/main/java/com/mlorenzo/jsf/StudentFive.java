package com.mlorenzo.jsf;

import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class StudentFive {
	private String firstName;
	private String lastName;
	
	// since the usar can select multiple languages, we need an array of strings
	private String[] favoriteLanguages;
	
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

	public String[] getFavoriteLanguages() {
		return favoriteLanguages;
	}

	public void setFavoriteLanguages(String[] favoriteLanguages) {
		this.favoriteLanguages = favoriteLanguages;
	}
	
}
