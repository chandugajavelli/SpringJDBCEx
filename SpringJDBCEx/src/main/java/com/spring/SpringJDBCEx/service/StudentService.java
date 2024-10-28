package com.spring.SpringJDBCEx.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.SpringJDBCEx.model.Student;
import com.spring.SpringJDBCEx.repo.StudentRepo;


@Service
public class StudentService {


    private StudentRepo repo;
    
    public void addStudent(Student s)  {
        // TODO Auto-generated method stub
       System.out.println("added");
       repo.save(s);

    }

    public StudentRepo getRepo() {
        return repo;
    }
    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public java.util.List<Student> getStudents() {
        // TODO Auto-generated method stub
     return repo.findAll();
    }

    

}
