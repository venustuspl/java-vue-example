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
public interface UserRepository extends JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {

    @Query(value = "select * from users where email like %:email% order by email",
            countQuery = "select count(*) from users where email like %:email%",
            nativeQuery = true)
    List<User> findByAnyPartOfEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> deleteById(Long id);
}
