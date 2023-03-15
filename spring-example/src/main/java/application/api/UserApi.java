package application.api;


import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllTutorials() {
        try {

            List<User> users = new ArrayList<User>(userRepository.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTutorial(@RequestBody User user) {
        try {
            userRepository.save(new User(user.getLogin(), user.getPassword()));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        try {
            int result = userRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("User was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(String.valueOf(id));

        if (_user != null) {
            _user.setId(String.valueOf(id));
            _user.setLogin(user.getLogin());
            _user.setPassword(user.getPassword());
            _user.setPoints(user.getPoints());

            userRepository.update(_user);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}
