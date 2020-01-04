package com.schoolcatalog.app.controllers;

import com.schoolcatalog.app.utils.SessionInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * StudentController
 */
@RestController
public class StudentController {

  @Autowired
  private SessionInfo session;

  @RequestMapping(value = "/showstudentpage")
  public ModelAndView showStudentPage() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("student");
    modelAndView.addObject("student", session.getUser());
    return modelAndView;
  }
  
}