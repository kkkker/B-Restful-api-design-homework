package com.thoughtworks.capability.gtb.restfulapidesign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private int id;
    private String name;
    private String note;
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
