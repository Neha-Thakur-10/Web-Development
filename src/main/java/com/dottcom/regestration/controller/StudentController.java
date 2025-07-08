package com.dottcom.regestration.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dottcom.regestration.model.Student;
import com.dottcom.regestration.repository.StudentRepository;
import com.dottcom.regestration.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private StudentService studentService;

    // Home page → register form
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    // Form submission from register.html
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("student") Student student,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        studentService.save(student);
        return "redirect:/UserData";
    }

    // JSON submission from Postman
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerFromPostman(@RequestBody @Valid Student student) {
        repo.save(student);
        return ResponseEntity.ok("Saved via JSON");
    }

    // Show all students
    @GetMapping("/UserData")
    public String showAllStudents(Model model) {
        List<Student> students = repo.findAll();
        model.addAttribute("students", students);
        return "UserData";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("student", student);
        return "editUser";
    }

    // Update student (form)
    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult result,
                                RedirectAttributes redirectAttrs,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "editUser"; // return to the edit form with errors
        }

        repo.save(student);
        redirectAttrs.addFlashAttribute("msg", "Student updated successfully!");
        return "redirect:/UserData";
    }


    // Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id, RedirectAttributes redirectAttrs) {
        repo.deleteById(id);
        redirectAttrs.addFlashAttribute("msg", "Student deleted.");
        return "redirect:/UserData";
    }

    // Generate PDF report
    @GetMapping("/report")
    public void generateReport(HttpServletResponse response) throws Exception {
        List<Student> students = repo.findAll();
        studentService.exportStudentReport(students, response);
    }

    // Global exception handler
    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(ConstraintViolationException.class)
        public String handleValidationException(ConstraintViolationException ex, Model model) {
            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            model.addAttribute("errors", errors);
            model.addAttribute("student", new Student()); // repopulate form
            return "register";
        }
    }
}
