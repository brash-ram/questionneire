package ru.rsreu.questionnaire.dto;

import ru.rsreu.questionnaire.data.entity.Present;

import java.util.List;
import java.util.Set;

public record SaveQuestionnaireDTO(
         String name,
         Boolean isCome,
         Boolean transport,
         Set<Long> presents,
         List<String> satellites,
         List<String> alcohol
) {
}
