package ru.rsreu.questionnaire.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rsreu.questionnaire.data.entity.Present;

@Repository
public interface PresentRepository extends JpaRepository<Present, Long> {
}