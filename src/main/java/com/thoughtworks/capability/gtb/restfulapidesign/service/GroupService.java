package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NoSuchGroupIdFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final Map<Integer, Group> groupDataBase = new HashMap<>();
    private final int GROUP_NUMBERS = 6;

    private final StudentService studentService;

    @Autowired
    public GroupService(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<Group> divideStudents() {
        List<Student> shuffleStudents = this.studentService.getStudents(null);
        Collections.shuffle(shuffleStudents);
        int studentSize = shuffleStudents.size();
        int groupNumbers = Math.min(studentSize, GROUP_NUMBERS);
        return divideStudentsWithGroup(groupNumbers, shuffleStudents);
    }

    private List<Group> divideStudentsWithGroup(int groupNumbers, List<Student> students) {
        List<Group> studentGroups = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (i < GROUP_NUMBERS) {
                Group group = new Group();
                group.setId(i + 1);
                groupDataBase.put(i + 1, group);
            }
            groupDataBase.get((i % groupNumbers) + 1).addStudent(students.get(i));
        }
        return groupDataBase.values().stream()
                .sorted(Comparator.comparing(Group::getId))
                .collect(Collectors.toList());
    }

    public Group updateGroup(int id, Group group) throws Exception {
        if (!groupDataBase.containsKey(id)) {
            throw new NoSuchGroupIdFoundException();
        }
        groupDataBase.get(id).setName(group.getName());
        return groupDataBase.get(id);
    }
}
