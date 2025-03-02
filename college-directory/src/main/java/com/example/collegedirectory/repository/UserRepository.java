package com.example.collegedirectory.repository;

import javax.swing.Spring;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.collegedirectory.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String Username);
    //Custom query method to find a user by their username
    //UserRepository Interface:

// It extends JpaRepository<User, Long>: This is where JpaRepository is a special interface in Spring Data JPA that provides built-in methods like:
// save(): To save an entity (insert or update).
// findById(): To find an entity by its primary key (e.g., id).
// delete(): To delete an entity.
// findAll(): To get all entities.
// JpaRepository takes two parameters:
// The entity type (User).
// The type of the entity's primary key (Long in this case, because the id field in the User entity is of type Long).

// Custom Method (findByUsername):

// You can define custom query methods based on the naming convention. For example, findByUsername(String username) will automatically generate the query to find a User by their username.
// You don't need to write the query manually; Spring Data JPA will generate the necessary SQL behind the scenes.

// How Spring Boot works:

// When you run the application, Spring Boot will automatically implement the methods defined in the JpaRepository interface (such as save, findById, etc.) without needing explicit implementation in the UserRepository interface.
// Spring Boot will also handle any custom query methods you define.
}
