package com.schoolcatalog.app.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Admin
 */
@Document(collection = "admins")
public class Admin extends Person {
  //CRUD operations for each type of user
  
}