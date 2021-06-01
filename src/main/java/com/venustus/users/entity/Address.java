package com.venustus.users.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    @Id
    @Column
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @NotNull(message = "{NotNull.Address.street}")
    private String street;

    @Column
    @NotNull(message = "{NotNull.Address.postalCode}")
    private String postalCode;

    @Column
    @NotNull(message = "{NotNull.Address.city}")
    private String city;

    @Column
    @NotNull(message = "{NotNull.Address.country}")
    private String country;

    @Column
    @NotNull(message = "{NotNull.Address.phone}")
    private String phone;
}
