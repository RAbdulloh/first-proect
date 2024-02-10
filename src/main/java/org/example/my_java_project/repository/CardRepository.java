package org.example.my_java_project.repository;

import org.example.my_java_project.module.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByCardIdAndDeleteAtIsNull(Integer cardId);

    Boolean existsByCardNumberAndDeleteAtIsNull(String card);

    List<Card> findAllByUserIdAndDeleteAtIsNull(Integer userId);

}
