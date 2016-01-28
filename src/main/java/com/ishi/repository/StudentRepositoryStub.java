package com.ishi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ishi.model.Student;

public class StudentRepositoryStub implements StudentRepository {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	// Register the student and make entry in the database
	@Override
	public void create(Student student) {
		String SQL = "INSERT INTO `Internship`.`student` (`first_name`, `last_name`,"
				+ " `email`, `mobile`, `address`, `city`, `pincode`, `state`, `country`, `courses`)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String email = student.getEmail();
		String mobile = student.getMobile();
		String address = student.getAddress();
		String pincode = student.getPincode();
		String city = student.getCity();
		String state = student.getState();
		String country = student.getCountry();
		String courses = student.getCourses();

		// jdbcTemplateObject.update(SQL, student);
		jdbcTemplateObject.update(SQL, firstName, lastName, email, mobile, address, city, pincode, state, country, courses);
		System.out.println("Created Record: \tName = " + firstName);
		return;
	}

	
	// Register the student and make entry in the database
	@Override
	public void update(Student student, String id) {
//		String SQL = "INSERT INTO `Internship`.`student` (`first_name`, `last_name`,"
//				+ " `email`, `mobile`, `address`, `city`, `pincode`, `state`, `country`, `courses`)"
//				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		String sql = "UPDATE `Internship`.`student` SET `first_name`='?', `last_name`='?', `email`='?', `mobile`='?',"
				+"`address`='?', `city`='?', `pincode`='?', `state`='?', `country`='?', `courses`='?' WHERE `id`='?'";
		
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String email = student.getEmail();
		String mobile = student.getMobile();
		String address = student.getAddress();
		String pincode = student.getPincode();
		String city = student.getCity();
		String state = student.getState();
		String country = student.getCountry();
		String courses = student.getCourses();

		// jdbcTemplateObject.update(SQL, student);
		jdbcTemplateObject.update(sql, new Object[] { firstName, lastName, email, mobile, address, city, pincode, state, country, courses, id});
		System.out.println("Updated Record: \tName = " + firstName);
		return;
	}
	
	
	// Delete the student account having ID=id
	@Override
	public void delete(Integer id) {
		String SQL = "delete from `student` where id = ?";
		
		try {
		String SQL2 = "select * from student where id = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL2, new Object[] { id }, new StudentMapper());

		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id + ", Firstname = "+student.getFirstName()+ ", Lastname = "+student.getLastName());
		} 
		
		catch (org.springframework.dao.EmptyResultDataAccessException e) {
			System.out.println("Student with ID="+id+" does not exist!");
		}
		
		return;
	}
	
	

	// Show data of the particular student
	@Override
	public Student getStudent(String id) {
		
		try {
		String SQL = "select * from student where id = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new StudentMapper());
		return student;
		} 
		
		catch (org.springframework.dao.EmptyResultDataAccessException e) {
			System.out.println("Student with ID="+id+" does not exist!");
		}
		
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		
		System.out.println("WE are in this getAllStudents");
		
		String SQL = "select * from student";
		
		
		List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());

		return students;
	}

	// Still need to work on........
	@Override
	public void update(Integer id, Integer age) {
		String SQL = "update student set age = ? where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

}