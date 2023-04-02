package ru.rsreu.questionnaire.dto;

import java.util.List;

public record DataForStartQuestionnaireResponse(
        String name,
        List<PresentResponse> presents
) {
}
