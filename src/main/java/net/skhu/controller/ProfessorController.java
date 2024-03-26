package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import net.skhu.dto.Department;
import net.skhu.dto.Professor;
import net.skhu.model.ProfessorEdit;
import net.skhu.service.DepartmentService;
import net.skhu.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {

    @Autowired ProfessorService ProfessorService;
    @Autowired DepartmentService departmentService;

    @GetMapping("list")
    public String list(Model model) {
        List<Professor> Professors = ProfessorService.findAll();
        model.addAttribute("professors", Professors);
        return "professor/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        ProfessorEdit ProfessorEdit = new ProfessorEdit();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("professorEdit", ProfessorEdit);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    @PostMapping("create")
    public String create(Model model,
            @Valid ProfessorEdit ProfessorEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }
        Professor Professor2 = ProfessorService.findByProfessorId(ProfessorEdit.getId());
        if (Professor2 != null) {
            bindingResult.rejectValue("Office", null, "교수실이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }
        ProfessorService.insert(ProfessorEdit);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, int id) {
        ProfessorEdit ProfessorEdit = ProfessorService.findOne(id);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("professorEdit", ProfessorEdit);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    @PostMapping("edit")
    public String edit(Model model,
            @Valid ProfessorEdit ProfessorEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }
        Professor Professor2 = ProfessorService.findByProfessorId(ProfessorEdit.getId());
        if (Professor2 != null && Professor2.getId() != ProfessorEdit.getId()) {
            bindingResult.rejectValue("Office", null, "교수실이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }
        ProfessorService.update(ProfessorEdit);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Model model, int id) {
        ProfessorService.delete(id);
        return "redirect:list";
    }
}
