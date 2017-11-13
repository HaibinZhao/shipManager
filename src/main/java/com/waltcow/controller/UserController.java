package com.waltcow.controller;

import com.waltcow.repository.UserRepository;
import com.waltcow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by waltcow on 04/11/2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    /**
     * Injects UserRepository instance
     * @param userRepository to inject
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/current", method = GET)
    public ResponseEntity currentUser(Principal principal) {

        String username = principal.getName();
        User me = userRepository.findByName(username);

        return ResponseEntity.ok(me);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = GET)
    public ResponseEntity admin() {
        return ResponseEntity.ok("Greetings from admin protected method!");
    }
}
