package com.spring.SpringJDBCEx;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.SpringJDBCEx.model.Student;
import com.spring.SpringJDBCEx.service.StudentService;


@SpringBootApplication
public class SpringJdbcExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcExApplication.class, args);
		System.out.println("helloworld");

		Student s = context.getBean(Student.class);
		s.setName("navin");
		s.setMarks(77);
		s.setRollNo(18);

		
		StudentService service = context.getBean(StudentService.class);
		service.addStudent(s);

		List<Student> students = service.getStudents();
		System.out.println(students);
	}

}
