package application.repository;

import application.model.User;

import java.util.List;

public interface UserRepository {
    int save(User user);

    int update(User user);

    User findById(String id);

    int deleteById(Long id);

    List<User> findAll();
}
