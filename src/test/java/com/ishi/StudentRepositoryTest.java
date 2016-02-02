package com.ishi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
		try (FileWriter file = new FileWriter("./src/main/webapp/app/data/file1.json")) {
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
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateJson() {
		
	    String path = String.format("%s/%s", System.getProperty("user.dir"), this.getClass().getPackage().getName().replace(".", "/"));
	    System.out.println(path + "hey");
		
		
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("./src/main/webapp/app/data/scrum.json"));

			JSONArray a = (JSONArray) obj;
			
			Iterator<JSONObject> iterator = a.iterator();
			while (iterator.hasNext()) {
				//System.out.println(iterator.next().get("date"));
				
				JSONObject i = iterator.next();
				
				if(i.get("date").equals("2016-01-29")) {
					System.out.println(i.get("date"));
					System.out.println(i.get("tasks"));					
				
					Object tasks = i.get("tasks");
					JSONArray tasks2 = (JSONArray) tasks;
					tasks2.add("New Tasks");
					
					
					i.remove("tasks");
					i.put("tasks", tasks2);
					
					System.out.println(i.get("tasks"));
					
				}
			}
			
			
			try (FileWriter file = new FileWriter("./src/main/webapp/app/data/file1.json")) {
				try {
					file.write(a.toJSONString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Successfully Added task to the file...");
				System.out.println("\nJSON Object: " + obj);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
