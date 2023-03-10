package ru.rsreu.questionnaire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rsreu.questionnaire.dto.CreateQuestionnaireDTO;

@RestController
public class QuestionnaireController{

    @GetMapping("/create")
    public ResponseEntity<Object> createQuestionnaire(@RequestBody CreateQuestionnaireDTO createQuestionnaire) {
        return null;
    }
}
