package com.waltcow.controller;

import com.alibaba.fastjson.JSONObject;
import com.waltcow.model.Role;
import com.waltcow.model.RoleName;
import com.waltcow.model.User;
import com.waltcow.security.JwtAuthenticationRequest;
import com.waltcow.security.JwtUtil;
import com.waltcow.security.JwtUser;
import com.waltcow.service.UserService;
import com.waltcow.utility.HttpResponseEnum;
import com.waltcow.utility.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

/**
 * AuthController provides sign_up, sign_in and token refresh methods
 * @author waltcow
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${auth.header}")
    private String tokenHeader;

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserService userService;

    /**
     * Injects AuthenticationManager instance
     * @param authenticationManager to inject
     */
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Injects JwtUtil instance
     * @param jwtUtil to inject
     */
    @Autowired
    public void setJwtTokenUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Injects UserDetailsService instance
     * @param userDetailsService to inject
     */
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Injects UserService instance
     * @param userService to inject
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Adds new user and returns authentication token
     * @param authenticationRequest request with username, email and password fields
     * @return generated JWT
     * @throws AuthenticationException
     */
    @PostMapping("/signup")
    public WebResult createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String name = authenticationRequest.getUsername();
        final String email = authenticationRequest.getEmail();
        final String password = authenticationRequest.getPassword();

        log.debug("generate token for user: " + name);

        if (this.userService.findByName(name) != null) {
            return WebResult.failure(HttpResponseEnum.SIGN_UP_USER_EXIST.getCode(), HttpResponseEnum.SIGN_UP_USER_EXIST.getMsg());
        }

        if (this.userService.findByEmail(email) != null) {
            return WebResult.failure(HttpResponseEnum.SIGN_UP_EMAIL_EXIST.getCode(), HttpResponseEnum.SIGN_UP_EMAIL_EXIST.getMsg());
        }

        Role role = new Role(1L, RoleName.ROLE_USER);
        userService.save(new User(0L, name, email, password, true, role));
        JwtUser userDetails;

        try {
            userDetails = (JwtUser) userDetailsService.loadUserByUsername(name);
        } catch (UsernameNotFoundException ex) {
            log.error(ex.getMessage());
            return WebResult.failure(HttpResponseEnum.USER_NOT_EXIST.getCode(), HttpResponseEnum.USER_NOT_EXIST.getMsg());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return WebResult.error(ex.getMessage());
        }

        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(name, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JSONObject result = new JSONObject();
        result.put("token", jwtUtil.generateToken(userDetails));

        return WebResult.success(result);
    }

    /**
     * Returns authentication token for given user
     * @param authenticationRequest with username and password
     * @return generated JWT
     * @throws AuthenticationException
     */
    @PostMapping("/signin")
    public WebResult getAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        final String name = authenticationRequest.getUsername();
        final String password = authenticationRequest.getPassword();
        log.debug("get token from user" + name);
        JwtUser userDetails;

        try {
            userDetails = (JwtUser) userDetailsService.loadUserByUsername(name);
        } catch (UsernameNotFoundException | NoResultException ex) {
            log.error(ex.getMessage());
            return WebResult.failure(HttpResponseEnum.USER_NOT_EXIST.getCode(), HttpResponseEnum.USER_NOT_EXIST.getMsg());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return WebResult.error(ex.getMessage());
        }

        if(!passwordEncoder().matches(password, userDetails.getPassword())) {
            return WebResult.failure(HttpResponseEnum.PASSWORD_ERROR.getCode(), HttpResponseEnum.PASSWORD_ERROR.getMsg());
        }

        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(name, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JSONObject result = new JSONObject();
        result.put("token", jwtUtil.generateToken(userDetails));

        return WebResult.success(result);
    }

    /**
     * Refreshes token
     * @param request with old JWT
     * @return Refreshed JWT
     */
    @PostMapping("/token/refresh")
    public WebResult refreshAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        log.info("[POST] REFRESHING TOKEN");

        String refreshedToken = jwtUtil.refreshToken(token);

        JSONObject result = new JSONObject();
        result.put("token", refreshedToken);

        return WebResult.success(result);
    }

}
