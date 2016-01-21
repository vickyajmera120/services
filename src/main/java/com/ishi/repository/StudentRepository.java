package com.ishi.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ishi.model.Student;

public interface StudentRepository {

	void setDataSource(DataSource dataSource);

	//Register the student and make entry in the database
	void create(Student student);

	//Show data of the particular student
	Student getStudent(String id);

	List<Student> getAllStudents();

	//Delete the student account having ID=id
	void delete(Integer id);

	//Still need to work on........
	void update(Integer id, Integer age);

}