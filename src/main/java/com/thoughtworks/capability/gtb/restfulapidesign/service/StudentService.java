package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NoSuchStudentIdFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Student> getStudents(String gender) {
        return studentDataBase.values().stream()
                .filter(student -> gender == null || gender.equals(student.getGender()))
                .collect(Collectors.toList());
    }

    public Student getStudent(int id) throws Exception {
        if (!studentDataBase.containsKey(id)) {
            throw new NoSuchStudentIdFoundException();
        }
        return studentDataBase.get(id);
    }


    public Student updateStudent(int id, Student student) throws Exception {
        if (!studentDataBase.containsKey(id)) {
            throw new NoSuchStudentIdFoundException();
        }
        student.setId(id);
        studentDataBase.replace(id, student);
        return student;
    }
}
