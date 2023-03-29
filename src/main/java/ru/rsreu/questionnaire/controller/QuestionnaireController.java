package ru.rsreu.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.dto.*;
import ru.rsreu.questionnaire.service.PeopleService;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController{
    private final PeopleService peopleService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuestionnaire(@RequestBody CreateQuestionnaireRequest createQuestionnaire) {
        People people = peopleService.persistPeople(createQuestionnaire.name());
        DataForQuestionnaireRequest data = peopleService.getDataForNewQuestionnaire(people.getId());
        return ResponseEntity.ok().body(data);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveQuestionnaire(@RequestBody SaveQuestionnaireRequest saveQuestionnaire) {
        peopleService.saveQuestionnaire(saveQuestionnaire);
        return ResponseEntity.ok().body("Saved successfully!");

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDataQuestionnaire() {
        AllQuestionneiresResponse dto = peopleService.getAllData();
        return ResponseEntity.ok().body(dto);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.toString(),
                ex.getClass().getName(),
                ex.getMessage(),
                Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList())
        ));
    }
}
