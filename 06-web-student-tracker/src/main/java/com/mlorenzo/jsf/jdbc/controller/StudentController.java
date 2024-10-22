package com.mlorenzo.jsf.jdbc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.mlorenzo.jsf.jdbc.dao.StudentDao;
import com.mlorenzo.jsf.jdbc.model.Student;

// Un Managed Bean de JSF debe tener un constructor vacío y métodos getter y setter para sus propiedades.

// Nota: En este caso, establecemos el scope o ámbito de este bean de JSF en Session porque lo más lógico es crear un controlador de estudiantes
// por cada usuario o cliente (Por ejemplo, un navegador web) que utilice la aplicación y que se mantenga vivo durante la duración de cada sesión.

// Esta anotación hace que la clase se convierta en un Managed Bean de JSF, es decir, un bean manejado o gestionado
// por JSF.
@ManagedBean
@SessionScoped
public class StudentController {
	private final static Logger logger = Logger.getLogger(StudentController.class.getName());
	
	private final StudentDao studentDao;
	private List<Student> students;
	
	public StudentController() throws NamingException {
		studentDao = StudentDao.getInstance();
	}
	
	public void loadStudents() {
		logger.info("Loading students");
		
		try {
			// get all students from database
			students = studentDao.getStudents();
		}
		catch(SQLException e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading students", e);
			
			// add error message for JSF page
			addErrorMessage(e);
		}
	}
	
	public String loadStudent(int studentId) {
		logger.info("Loading student " + studentId);
		
		try {
			// get student from database
			Student student = studentDao.getStudent(studentId);
			
			// put it a request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("student", student);
		}
		catch(Exception e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading student with id: " + studentId, e);
			
			// add error message for JSF page
			addErrorMessage(e);
			
			return null;
		}
		
		return "update-student-form";
	}
	
	public String addStudent(Student student) {
		logger.info("Adding student: " + student);
		
		try {
			// add student to the database
			studentDao.addStudent(student);
		}
		catch(SQLException e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding student", e);
			
			// add error message for JSF page
			addErrorMessage(e);
			
			return null;
		}
		
		// redirect to main page (the students list)
		// Nota: Usamos el parámetro "faces-redirect=true" en vez de solo el nombre de la vista "list-students" para evitar el caso de que
		// el usuario recargue la página del navegador y se vuelva a crear el usuario de nuevo en la base de datos.
		return "list-students?faces-redirect=true";
	}
	
	public String updateStudent(Student student) {
		logger.info("Updating student: " + student);
		
		try {
			// update student in the database
			studentDao.updateStudent(student);
		}
		catch(SQLException e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating student: " + student, e);
			
			// add error message for JSF page
			addErrorMessage(e);
			
			return null;
		}
		
		// redirect to main page (the students list)
		// Nota: Usamos el parámetro "faces-redirect=true" en vez de solo el nombre de la vista "list-students" para evitar el caso de que
		// el usuario recargue la página del navegador y se vuelva a actualizar el usuario de nuevo en la base de datos (En este caso, no
		// afectaría a la base de datos porque la operación de actualización es idempotente pero la sentencia SQL sí se vuelve a ejecutar).
		return "list-students?faces-redirect=true";
	}
	
	public String deleteStudent(int studentId) {
		logger.info("Deleting student " + studentId);
		
		try {
			// delete the student from the database
			studentDao.deleteStudent(studentId);
		}
		catch(SQLException e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting student with id: " + studentId, e);
			
			// add error message for JSF page
			addErrorMessage(e);
			
			return null;
		}
		
		// redirect to main page (the students list)
		// Nota: Usamos el parámetro "faces-redirect=true" en vez de solo el nombre de la vista "list-students" para evitar el caso de que
		// el usuario recargue la página del navegador y se vuelva a eliminar el usuario de nuevo en la base de datos (En este caso, no
		// afectaría a la base de datos porque la operación de eliminación es idempotente pero la sentencia SQL sí se vuelve a ejecutar).
		return "list-students?faces-redirect=true";
	}

	public List<Student> getStudents() {
		return students;
	}
	
	private void addErrorMessage(Exception e) {
		FacesMessage message = new FacesMessage("Error: " + e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
