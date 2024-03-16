package com.diaz.expense_tracker.service_impl;

import com.diaz.expense_tracker.Repository.UserRepository;
import com.diaz.expense_tracker.dto.UserDto;
import com.diaz.expense_tracker.dto.UserUpdateDto;
import com.diaz.expense_tracker.model.User;
import com.diaz.expense_tracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userUpdateDto.getId()));

        updateUserFromDto(existingUser, userUpdateDto);

        userRepository.save(existingUser);
    }

    private void updateUserFromDto(User existingUser, UserUpdateDto userUpdateDto) {
        existingUser.setFirstName(userUpdateDto.getFirstName());
        existingUser.setLastName(userUpdateDto.getLastName());
        existingUser.setEmail(userUpdateDto.getEmail());
        // Consider encrypting the password if it's not already handled
        existingUser.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
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
