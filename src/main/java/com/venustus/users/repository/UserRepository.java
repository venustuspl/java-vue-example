package com.venustus.users.repository;

import com.venustus.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource()
public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {

    @Query(value = "select * from user where email like %:email% order by email",
            countQuery = "select count(*) from user where email like %:email%",
            nativeQuery = true)
    List<User> findByAnyPartOfEmail(String email);

    @Query(value = "select * from user where login like %:login% order by login",
            countQuery = "select count(*) from user where login like %:login%",
            nativeQuery = true)
    List<User> findByAnyPartOfLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> deleteUserById(Long id);
}
