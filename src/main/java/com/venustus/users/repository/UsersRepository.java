package com.venustus.users.repository;

import com.venustus.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource()
public interface UsersRepository extends JpaRepository<Users, Integer>,
        JpaSpecificationExecutor<Users>, QuerydslPredicateExecutor<Users> {

    List<Users> findByPartOfEmail(String email);

    Optional<Users> findByEmail(String email);

}
