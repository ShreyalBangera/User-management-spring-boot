package com.example.crud.controller;

import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentRepo.findById(id).orElse(null));
        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/";
    }
}