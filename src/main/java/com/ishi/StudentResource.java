package com.ishi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ishi.model.Student;
import com.ishi.repository.StudentRepository;

@Path("")		//	http://localhost:8080/services/services/internship

public class StudentResource {
	
	ApplicationContext context = new FileSystemXmlApplicationContext("Beans.xml");	
	StudentRepository studentRepository = (StudentRepository) context.getBean("studentRepositoryStub");

	//Retrieves the data of all the students registered under the database
	@GET
	@Path("students")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Student> getAllStudents() {
		
		//System.out.println(studentRepository.getAllStudents());
		return studentRepository.getAllStudents();
		
	}
	
	//Retrieves the data of the student with given ID
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("students/{studentId}")
	public Response getStudent(@PathParam("studentId") String studentId) {
		
		Student student = studentRepository.getStudent(studentId);
		
		if(student == null)
			return Response.status(Status.NOT_FOUND).build();
		
		else
			return Response.ok().entity(student).build();
	}
	
	
	//Deletes the record from the database with given ID
	@DELETE
	@Path("delete/{studentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam("studentId") Integer studentId) {
		
		studentRepository.delete(studentId);
		
		return Response.ok().build();
	}
	
	//Register a student on the site
	@POST
	@Path("register_success.html")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Student createStudent(MultivaluedMap<String, String> formParams) {
		
		System.out.println("registration successful");
		Student student = new Student();
		
		student.setFirstName(formParams.getFirst("first_name"));
		student.setLastName(formParams.getFirst("last_name"));
		
		student.setEmail(formParams.getFirst("email"));
		student.setMobile(formParams.getFirst("mobile"));
		student.setAddress(formParams.getFirst("address"));
		
		student.setCity(formParams.getFirst("city"));
		student.setPincode(formParams.getFirst("pincode"));
		student.setState(formParams.getFirst("state"));
		student.setCountry(formParams.getFirst("country"));
		
		studentRepository.create(student);
		
		return student;
	}
	
	//Update the profile of the student
	@POST
	@Path("update/student/{StudentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Student updateStudent(@PathParam("studentId") String id, Student student) {
		
		studentRepository.update(student, id);
		
		return student;
	}
	
	
}