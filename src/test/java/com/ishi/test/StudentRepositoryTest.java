package com.ishi.test;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ishi.model.Student;
import com.ishi.repository.StudentRepository;

public class StudentRepositoryTest {

	//ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/Beans.xml");
	ApplicationContext context = new FileSystemXmlApplicationContext("file:src/main/resources/Beans.xml");
	
	StudentRepository studentRepository = (StudentRepository) context.getBean("studentRepositoryStub");
	
	@Test
	public void testUpdate() {
		System.out.println("------Records Update--------");
		Student student = new Student();
		
		student.setFirstName("Dilip");
		student.setLastName("Mirchandani");
		
		studentRepository.update(student,"31");
		System.out.println();
	}
	
	
	
	@Test
	public void testCreate() {
		System.out.println("------Records Creation--------");
		Student student = new Student();
		
		student.setFirstName("Dilip");
		student.setLastName("Mirchandani");
		
		studentRepository.create(student);
		System.out.println();
	}
	
	@Test
	public void testDelete() {
		//StudentClient client = new StudentClient();
		
		//client.delete(20);
	
		studentRepository.delete(20);
	}
	
	@Test
	public void testRead() {
		
		System.out.println("----Listing Record with ID =  -----");
		
		Student student = studentRepository.getStudent("12");
		System.out.println("ID : " + student.getId());
		System.out.println("First Name : " + student.getFirstName());
		System.out.println("Last Name : " + student.getLastName());
		System.out.println();
	}
	
	
	//Testing JSON file writing
	@SuppressWarnings("unchecked")
	@Test
	public void testJson() {
		JSONObject obj = new JSONObject();
		obj.put("Name", "crunchify.com");
		obj.put("Author", "App Shah");
 
		JSONArray company = new JSONArray();
		company.add("Compnay: eBay");
		company.add("Compnay: Paypal");
		company.add("Compnay: Google");
		company.add("Compnay: ISHI");
		obj.put("Company List", company);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("/home/vicky.ajmera/workspace2/file1.txt")) {
			try {
				file.write(obj.toJSONString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
