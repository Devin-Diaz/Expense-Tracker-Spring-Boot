package com.diaz.expense_tracker.service;

import com.diaz.expense_tracker.dto.UserDto;
import com.diaz.expense_tracker.dto.UserUpdateDto;
import com.diaz.expense_tracker.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void saveUser(UserDto userDto);

    void updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    User findById(Long id);


}
