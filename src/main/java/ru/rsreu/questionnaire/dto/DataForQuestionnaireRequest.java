package ru.rsreu.questionnaire.dto;

import java.util.List;

public record DataForQuestionnaireRequest(
        Long id,
        String name,
        List<PresentResponse> presents
){}
