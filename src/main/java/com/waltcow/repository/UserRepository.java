package com.waltcow.repository;

import com.waltcow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author waltcow
 * Provides basic CURD operations with User entity
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Finds user by email
     * @param email to look for
     * @return user by given email
     */
    User findByEmail(String email);

    /**
     * Finds user by name
     * @param name to look for
     * @return user by given name
     */
    User findByName(String name);
}
