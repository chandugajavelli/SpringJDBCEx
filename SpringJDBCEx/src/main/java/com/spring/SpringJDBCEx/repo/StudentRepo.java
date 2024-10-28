package com.spring.SpringJDBCEx.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import com.spring.SpringJDBCEx.model.Student;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;
    
    
    public void save(Student s) 
    {
        // Check if rollno already exists
        if (doesRollnoExist(s.getRollNo())) {
            System.out.println("Roll number " + s.getRollNo() + " already exists. Skipping insertion.");
            return; // Skip the insert operation
        }
    
        // Insert the new student record
        String sql = "insert into student (rollno, name, marks) values (?, ?, ?)";
        int rows = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());
        System.out.println(rows + " row(s) affected.");
    }
    
    private boolean doesRollnoExist(int rollno) {
        String sql = "SELECT COUNT(*) FROM student WHERE rollno = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, rollno);
        return count != null && count > 0;
    }

    public List<Student> findAll() {
        String sql = "select * from student";
        RowMapper<Student> mapper = new  RowMapper<Student>(){

            @Override
            @Nullable
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
              Student s = new Student();
              s.setRollNo(rs.getInt("rollno"));
              s.setName(rs.getString("name"));
              s.setMarks(rs.getInt("marks"));

              return s;
            }


        };

         return jdbc.query(sql,mapper);
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }



}
