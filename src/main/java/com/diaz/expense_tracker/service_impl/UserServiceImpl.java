package com.diaz.expense_tracker.impl;

import com.diaz.expense_tracker.Repository.UserRepository;
import com.diaz.expense_tracker.model.User;
import com.diaz.expense_tracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

        User existingUser = userRepository.findById(user.getId()).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user.getId()));

        updateUserHelper(existingUser, user);

        userRepository.save(existingUser);
    }

    private void updateUserHelper(User existingUser, User updatedUser) {
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();

        return null;
    }
}
