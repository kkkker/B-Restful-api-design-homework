package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NoSuchStudentIdFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Integer, Student> studentDataBase;

    public StudentService() {
        this.studentDataBase = new HashMap<>();
    }

    public Student addStudent(Student student) {
        int i = 1;
        while (studentDataBase.containsKey(i)) {
            i++;
        }
        student.setId(i);
        studentDataBase.put(i, student);
        return student;
    }

    public void deleteStudent(int id) throws Exception {
        if (!studentDataBase.containsKey(id)) {
            throw new NoSuchStudentIdFoundException();
        }
        studentDataBase.remove(id);
    }
}
