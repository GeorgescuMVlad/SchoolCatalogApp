package com.schoolcatalog.app.controllers;

import java.util.HashMap;
import java.util.List;

import com.schoolcatalog.app.models.Account;
import com.schoolcatalog.app.models.Admin;
import com.schoolcatalog.app.models.Student;
import com.schoolcatalog.app.models.Teacher;
import com.schoolcatalog.app.repositories.AccountRepository;
import com.schoolcatalog.app.repositories.AdminRepository;
import com.schoolcatalog.app.repositories.RoleRepository;
import com.schoolcatalog.app.repositories.StudentRepository;
import com.schoolcatalog.app.repositories.SubjectRepository;
import com.schoolcatalog.app.repositories.TeacherRepository;
import com.schoolcatalog.app.utils.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminController
 */
@RestController
public class AdminController {

  @Autowired
  AccountRepository accountRepo;

  @Autowired
  RoleRepository roleRepo;

  @Autowired
  SubjectRepository subjectRepo;

  @Autowired
  StudentRepository studentRepo;

  @Autowired
  TeacherRepository teacherRepo;

  @Autowired
  AdminRepository adminRepo;

  @RequestMapping(value = "admins")
  public ModelAndView showAdmins() {
    return new ModelAndView("redirect:/admin/admins");
  }

  @RequestMapping(value = "/admin/admins")
  public ModelAndView admins() {
    ModelAndView modelAndView = new ModelAndView();

    // retrieve all admins from database
    List<Admin> admins = adminRepo.findAll();
    // admins.stream().filter(it -> !it.getId().equals(id));

    modelAndView.setViewName("staff");
    modelAndView.addObject("role", "ADMIN");
    modelAndView.addObject("persons", admins);
    return modelAndView;
  }

  @RequestMapping(value = "teachers")
  public ModelAndView showTeachers() {
    return new ModelAndView("redirect:/admin/teachers");
  }

  @RequestMapping(value = "/admin/teachers")
  public ModelAndView teachers() {
    ModelAndView modelAndView = new ModelAndView();

    // retrieve all teachers from database
    List<Teacher> teachers = teacherRepo.findAll();

    modelAndView.setViewName("staff");
    modelAndView.addObject("role", "TEACHER");
    modelAndView.addObject("persons", teachers);
    return modelAndView;
  }

  @RequestMapping(value = "students")
  public ModelAndView showStudents() {
    return new ModelAndView("redirect:/admin/students");
  }

  @RequestMapping(value = "/admin/students")
  public ModelAndView students() {
    ModelAndView modelAndView = new ModelAndView();

    // retrieve all students from database
    List<Student> students = studentRepo.findAll();

    modelAndView.setViewName("staff");
    modelAndView.addObject("role", "STUDENT");
    modelAndView.addObject("persons", students);
    return modelAndView;
  }

  @RequestMapping(value = "/addPerson")
  public ModelAndView addPerson(@RequestParam String role) {
    switch (role) {
    case "ADMIN":
      return new ModelAndView("redirect:/admin/admins/add" + role);

    case "TEACHER":
      return new ModelAndView("redirect:/admin/teachers/add" + role);

    default:
      return new ModelAndView("redirect:/admin/students/add" + role);
    }
  }

  @RequestMapping(value = "/admin/admins/addADMIN")
  public ModelAndView addAdmin() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("addstaff");
    modelAndView.addObject("role", "ADMIN");
    return modelAndView;
  }

  @RequestMapping(value = "/admin/teachers/addTEACHER")
  public ModelAndView addTeacher() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("addstaff");
    modelAndView.addObject("role", "TEACHER");
    modelAndView.addObject("subjects", subjectRepo.findAll());
    return modelAndView;
  }

  @RequestMapping(value = "/admin/students/addSTUDENT")
  public ModelAndView addStudent() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("addstaff");
    modelAndView.addObject("role", "STUDENT");
    return modelAndView;
  }

  @RequestMapping(value = {"/admin/admins/save", "/admin/teachers/save", "/admin/students/save"})
  public ModelAndView savePerson(@RequestParam String role, @RequestParam String name, @RequestParam String email,
      @RequestParam String username, @RequestParam String password, @RequestParam String subject) {

    String target = "";
    switch (role) {
    case "ADMIN":
      target = "/admin/admins";
      Account adminAccount = createAccount(username, password, roleRepo.findByRole(role));
      accountRepo.save(adminAccount);
      Admin admin = new Admin();
      admin.setName(name);
      admin.setEmail(email);
      admin.setAccount(adminAccount);
      adminRepo.save(admin);
      break;

    case "TEACHER":
      target = "/admin/teachers";
      Account teacherAccount = createAccount(username, password, roleRepo.findByRole(role));
      accountRepo.save(teacherAccount);
      Teacher teacher = new Teacher();
      teacher.setName(name);
      teacher.setEmail(email);
      teacher.setAccount(teacherAccount);
      teacher.setSubject(subjectRepo.findByName(subject));
      teacherRepo.save(teacher);
      break;

    case "STUDENT":
      target = "/admin/students";
      Account studentAccount = createAccount(username, password, roleRepo.findByRole(role));
      accountRepo.save(studentAccount);
      Student student = new Student();
      student.setName(name);
      student.setEmail(email);
      student.setAccount(studentAccount);
      student.setGrades(new HashMap<>());
      studentRepo.save(student);
      break;

    default:
      break;
    }

    return new ModelAndView("redirect:" + target);
  }

  private Account createAccount(String username, String password, Role role) {
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    account.setRole(role);
    return account;
  }

  // @RequestMapping(value = "edit")
  // public ModelAndView editPerson() {
    
  // }

}