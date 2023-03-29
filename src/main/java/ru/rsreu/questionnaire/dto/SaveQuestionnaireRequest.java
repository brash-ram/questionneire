package ru.rsreu.questionnaire.dto;

import java.util.Set;

public record SaveQuestionnaireRequest(
         Long id,
         String name,
         Boolean isCome,
         Boolean transport,
         Set<Long> presents,
         Set<String> satellites,
         Set<String> alcohol
) {
}
