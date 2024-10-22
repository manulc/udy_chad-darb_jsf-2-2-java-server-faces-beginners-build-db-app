package com.mlorenzo.jsf.validatedemo;

import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class StudentThree {
	private String firstName;
	private String lastName;
	private String postalCode;
	private int freePasses;
	
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
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public int getFreePasses() {
		return freePasses;
	}
	
	public void setFreePasses(int freePasses) {
		this.freePasses = freePasses;
	}
	
}
