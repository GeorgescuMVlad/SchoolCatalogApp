package com.schoolcatalog.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * TeacherController
 */
@RestController
public class TeacherController {

  @RequestMapping(value = "/showteacherpage")
  public ModelAndView showTeacherPage() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:/teacher");
    return modelAndView;
  }
}