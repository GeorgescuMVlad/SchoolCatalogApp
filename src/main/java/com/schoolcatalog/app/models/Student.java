package com.schoolcatalog.app.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.schoolcatalog.app.utils.Group;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Student
 */
@Document(collection = "students")
public class Student extends Person {

  private HashMap<String, ArrayList<Integer>> grades;
  
  @DBRef
  private Group group;

  public HashMap<String, ArrayList<Integer>> getGrades() {
    return grades;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public void setGrades(HashMap<String, ArrayList<Integer>> grades) {
    this.grades = grades;
  }
}