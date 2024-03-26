package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;

@Service
public class StudentService {

    @Autowired StudentMapper studentMapper;
    ModelMapper modelMapper = new ModelMapper();

    public StudentEdit findOne(int id) {
        Student studentDto = studentMapper.findOne(id);
        return toEditModel(studentDto);
    }

    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public void insert(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.insert(student);
    }

    public void update(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.update(student);
    }

    public void delete(int id) {
        studentMapper.delete(id);
    }

    public Student toDto(StudentEdit studentEdit) {
        return modelMapper.map(studentEdit, Student.class);
    }

    public StudentEdit toEditModel(Student studentDto) {
        return modelMapper.map(studentDto, StudentEdit.class);
    }
}
