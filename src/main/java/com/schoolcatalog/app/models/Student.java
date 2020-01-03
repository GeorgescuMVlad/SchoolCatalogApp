package com.schoolcatalog.app.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.schoolcatalog.app.utils.Subject;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Student
 */
@Document(collection = "students")
public class Student extends Person {

  private HashMap<Subject, ArrayList<Integer>> grades;

  public HashMap<Subject, ArrayList<Integer>> getGrades() {
    return grades;
  }

  public void setGrades(HashMap<Subject, ArrayList<Integer>> grades) {
    this.grades = grades;
  }
  
}