package com.mlorenzo.jsf.jdbc.model;

import javax.faces.bean.ManagedBean;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Nota: Esta clase tiene que ser un bean de JSF porque lo usamos en el formulario para añadir un nuevo estudiante.
// Nota: En este caso, dejamos este bean de JSF con el scope Request (scope por defecto) porque lo utilizamos únicamente
// para enviar los datos del formulario y, entonces, lo más lógico es crear un nuevo bean por cada envío que se haga, es
// decir, un nuevo bean por cada petición http.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Student() {
	}
	
	public Student(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
