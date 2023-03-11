package ru.rsreu.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.dto.CreateQuestionnaireDTO;
import ru.rsreu.questionnaire.dto.DataForQuestionnaireDTO;
import ru.rsreu.questionnaire.dto.SaveQuestionnaireDTO;
import ru.rsreu.questionnaire.service.PeopleService;

@RestController
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController{
    private final PeopleService peopleService;

    @PostMapping("/create")
    public ResponseEntity<Object> createQuestionnaire(@RequestBody CreateQuestionnaireDTO createQuestionnaire) {
        try {
            People people = peopleService.persistPeople(createQuestionnaire.name());
            DataForQuestionnaireDTO data = peopleService.getDataForNewQuestionnaire(people.getId());
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Object> saveQuestionnaire(@RequestBody SaveQuestionnaireDTO saveQuestionnaire) {
        try {
            peopleService.saveQuestionnaire(saveQuestionnaire);
            return ResponseEntity.ok().body("Saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
