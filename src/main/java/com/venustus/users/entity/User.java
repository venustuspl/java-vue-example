package com.venustus.users.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "{NotNull.User.firstName}")
    private String firstName;

    @Column
    @NotNull(message = "{NotNull.User.lastName}")
    private String lastName;

    @Column
    @NotNull(message = "{NotNull.User.email}")
    private String email;

    public User() {
    }

    public User(@NotNull(message = "{NotNull.User.firstName}") String firstName, @NotNull(message = "{NotNull.User.lastName}") String lastName, @NotNull(message = "{NotNull.User.email}") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(long id, @NotNull(message = "{NotNull.User.firstName}") String firstName, @NotNull(message = "{NotNull.User.lastName}") String lastName, @NotNull(message = "{NotNull.User.email}") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
