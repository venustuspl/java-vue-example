package com.venustus.users.links;

import org.springframework.stereotype.Component;

@Component
public class UserLinks {
    public static final String LIST_USERS = "/users";
    public static final String ADD_USER = "/user";
    public static final String LIST_USERS_BY_EMAIL = "/users/email";
    public static final String LIST_USERS_BY_LOGIN = "/users/login";
    public static final String DELETE_USER = "/users/{id}/delete";
    public static final String UPDATE_USER = "/users/{id}/update";

    private UserLinks() {
    }
}
