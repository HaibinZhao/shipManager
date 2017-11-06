package com.waltcow.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by waltcow on 04/11/2017.
 */

@Entity(name = "shm_sailor")
@Data
public class Sailor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String duty;

    @NotNull
    @Column(nullable = false, unique = true)
    private String identity_id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String sailor_card_id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String sailor_cert_id;

    @NotNull
    @Column(nullable = false)
    private Boolean is_advanced;

    @NotNull
    @Column(nullable = false)
    private String mobile;

    @NotNull
    @Column(nullable = false)
    private String emergency_mobile;

    @NotNull
    @Column(nullable = false)
    private String address;

    public Sailor() {

    }
}

