package com.waltcow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "shm_users")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "name", length = 50, unique = true)
    @Size(min = 4, max = 50)
    private String name;

    @Email
    @Column(name = "email", length = 50, nullable = false)
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String name, String email, String password, Boolean enabled, Role role) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setEnabled(enabled);
        this.setRole(role);
    }

    public User(Long id, String name, String email, String password, Boolean enabled, Role role) {
        this(name, email, password, enabled, role);
        this.setId(id);
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
