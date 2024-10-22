package com.mlorenzo.jsf.validatedemo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class StudentFive {
	private String firstName;
	private String lastName;
	private String courseCode;
	
	// Método que realiza nuestra validación personalizada. Solo se aceptan códigos de curso que empiecen por "LUV".
	// Object value -> Se corresponde con el valor introducido por el usuario en el campo del formulario asociado.
	public void validateCourseCode(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		// No hay nada que validar
		if(value == null)
			return;
		
		String data = value.toString();
		
		// Course code must start with "LUV" .. if not, throw exception
		if(!data.startsWith("LUV")) {
			FacesMessage message = new FacesMessage("Course code must be start with \"LUV\"");
			throw new ValidatorException(message);
		}
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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
}
