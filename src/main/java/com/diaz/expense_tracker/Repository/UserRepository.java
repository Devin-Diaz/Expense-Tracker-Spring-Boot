package com.diaz.expense_tracker.Repository;

import com.diaz.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //helper jpa method for our CustomUsersDetailsService class
    //will be used to help authenticate users when logging in to webapp
    Optional<User> findByEmail(String email);
    
}
