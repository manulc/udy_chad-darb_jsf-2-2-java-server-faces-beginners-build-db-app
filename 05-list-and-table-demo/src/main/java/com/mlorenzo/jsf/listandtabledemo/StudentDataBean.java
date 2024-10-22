package com.mlorenzo.jsf.listandtabledemo;

import java.util.ArrayList;
import java.util.List;

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
public class StudentDataBean {
	private List<Student> students;
	
	public StudentDataBean() {
		loadSampleData();
	}
	
	public List<Student> getStudents() {
		return students;
	}

	private void loadSampleData() {
		students = new ArrayList<>();
		
		students.add(new Student("Mary", "Public", "mary.public@test.com"));
		students.add(new Student("John", "Doe", "john.doe@test.com"));
		students.add(new Student("Ajay", "Rao", "ajay.rao@test.com"));
	}
}
