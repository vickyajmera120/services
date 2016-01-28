package com.ishi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ishi.model.Student;

public class StudentMapper implements RowMapper<Student> {

	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setFirstName(rs.getString("first_name"));
		student.setLastName(rs.getString("last_name"));
		student.setEmail(rs.getString("email"));
		student.setAddress(rs.getString("address"));
		student.setCity(rs.getString("city"));
		student.setCountry(rs.getString("country"));
		student.setState(rs.getString("state"));
		student.setMobile(rs.getString("mobile"));
		student.setPincode(rs.getString("pincode"));
		student.setCourses(rs.getString("courses"));
		
		return student;
	}

}