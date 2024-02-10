package org.example.my_java_project.repository;

import org.example.my_java_project.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserIdAndDeleteAtIsNull(Integer userId);

    Boolean existsByUserEmailAndDeleteAtIsNull(String email);

    Boolean existsByUserIdAndDeleteAtIsNull(Integer userId);
}
