package com.hellokoding.springboot.repository;

import com.hellokoding.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by congle on 9/7/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
