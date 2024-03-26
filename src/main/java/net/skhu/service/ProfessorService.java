package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.Professor;
import net.skhu.mapper.ProfessorMapper;
import net.skhu.model.ProfessorEdit;

@Service
public class ProfessorService {

    @Autowired ProfessorMapper ProfessorMapper;
    ModelMapper modelMapper = new ModelMapper();

    public ProfessorEdit findOne(int id) {
        Professor ProfessorDto = ProfessorMapper.findOne(id);
        return toEditModel(ProfessorDto);
    }

    public Professor findByProfessorId(int id) {
        return ProfessorMapper.findByProfessorId(id);
    }

    public List<Professor> findAll() {
        return ProfessorMapper.findAll();
    }

    public void insert(ProfessorEdit ProfessorEdit) {
        Professor Professor = toDto(ProfessorEdit);
        ProfessorMapper.insert(Professor);
    }

    public void update(ProfessorEdit ProfessorEdit) {
        Professor Professor = toDto(ProfessorEdit);
        ProfessorMapper.update(Professor);
    }

    public void delete(int id) {
        ProfessorMapper.delete(id);
    }

    public Professor toDto(ProfessorEdit ProfessorEdit) {
        return modelMapper.map(ProfessorEdit, Professor.class);
    }

    public ProfessorEdit toEditModel(Professor ProfessorDto) {
        return modelMapper.map(ProfessorDto, ProfessorEdit.class);
    }
}
