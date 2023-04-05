package ru.rsreu.questionnaire.dto;

import java.util.Set;

public record SaveQuestionnaireRequest(
         Long id,
         Boolean isCome,
         String transport,
         Long presents,
         Set<String> satellites,
         Set<String> alcohol
) {
}
