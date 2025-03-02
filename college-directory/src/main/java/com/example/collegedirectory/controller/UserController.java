package com.example.collegedirectory.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.collegedirectory.User;
import com.example.collegedirectory.repository.UserRepository;
import com.example.collegedirectory.service.UserService;
//The controller layer handles HTTP requests and communicates with the service layer.
//Why Do We Need a Controller?
//Handles HTTP Requests:
//The controller receives HTTP requests (GET, POST, PUT, DELETE) from the client (frontend, Postman, or another system).
//It processes them using the service layer and returns the result as a response.

import jakarta.persistence.Converts;

// Separates Responsibilities:
// The controller only manages incoming requests and outgoing responses.
// The service layer contains the business logic (what to do with the data).
// The repository layer directly interacts with the database.

// RESTful API Design:

// Using a controller, we expose our User entity through RESTful endpoints (e.g., GET /users, POST /users, etc.).
// This allows any client (web, mobile, or another service) to communicate with our application.
@RestController//  Marks this class as a Spring REST Controller.
@RequestMapping("/users")//Sets the base URL for all endpoints (e.g., http://localhost:8080/users).
public class UserController {
    @Autowired
    private UserService userService;

    // Get all users
    // What it does?
    // When a GET request is made to /users, it fetches all users from the database.
    //Why?
    //So that we can retrieve all registered users in the system.
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // Get user by ID
    //What it does?
    //When a GET request is made to /users/{id}, it fetches a user by their ID.
    //Why?
    //To find details of a specific user.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }
    //What it does?
    //When a POST request is made to /users, it saves a new user in the database.
    //Why?
    //To allow new users to be added.

    //@PostMapping Annotation
    //This means the method handles HTTP POST requests.
    //When a POST request is sent to /users, this method will be triggered.
    @PostMapping
    //Returns: A User object (i.e., the newly created user).
    //Parameter:
    //@RequestBody User user: This tells Spring Boot to convert the incoming JSON request body into a User Java object.
    public User createUser(@RequestBody User user){
        //Calls the saveUser method from UserService,which:
        //Saves the user to the database using UserRepository.
        //Returns the saved User object.
        return userService.saveUser(user);
    }
//  Separates concerns:
//  Controller → Handles HTTP requests.
//  Service → Handles business logic.
//  Repository → Interacts with the database.
//  Converts JSON to Java Objects and vice versa.
    @PutMapping("/{id}")
//  What it does?
//  When a PUT request is made to /users/{id}, it updates the existing user's details.
//  Why?
//  To modify user information if needed.
// What is PUT in REST APIs?
// PUT is an HTTP method used to update a resource completely by replacing it with a new version. It is idempotent, meaning if you call the same PUT request multiple times, the result remains the same.
// How PUT Works?
// PUT is used when you want to update an entire resource (i.e., replace all fields).
// The request must include the full updated object.
// If the resource does not exist, some APIs create a new resource, but usually, PUT is only for updates.
// PUT is used for full updates (must send the entire object).
// ✔ If called multiple times with the same data, the result doesn't change (idempotent).
// ✔ If the resource doesn't exist, some APIs return 404 Not Found, while others create it.
// ✔ Use PATCH if you need to update only specific fields instead of replacing the whole object.
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
        User user = userService.updateUser(id, updatedUser);
        return (user !=null) ?  ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
     // Delete a user by ID
     //@DeleteMapping is an annotation in Spring Boot that maps HTTP DELETE requests to this method.
     //("/{id}") means the method will handle requests like:
    //DELETE /users/1
    //Here, 1 is the ID of the user to be deleted.
    //@PathVariable Long id
    //Extracts the id from the URL.
    @DeleteMapping("/{id}")
    //ResponseEntity<Void> means this method returns no data (Void).
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        //Calls the deleteUser(id) method from UserService to remove the user from the database.
        userService.deleteUser(id);
        //noContent() returns HTTP 204 No Content, meaning the request was successful, but there's no response body.
        return ResponseEntity.noContent().build();
//         .build() constructs and finalizes the HTTP response.
// ✔ Used in ResponseEntity to properly send responses like 204 No Content, 404 Not Found, etc.
// ✔ Without .build(), the response is incomplete and won’t be sent.
//difference between userservice and usercontroller
/*UserController.java = Manages API requests (e.g., POST, PUT, DELETE).
UserService.java = Manages business logic (e.g., saving, updating, deleting users).*/
    } 
}
