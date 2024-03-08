package com.diaz.expense_tracker.service;

import com.diaz.expense_tracker.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User findById(Long id);


}
