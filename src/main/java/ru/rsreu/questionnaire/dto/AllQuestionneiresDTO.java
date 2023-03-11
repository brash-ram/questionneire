package ru.rsreu.questionnaire.dto;

import ru.rsreu.questionnaire.data.entity.People;

import java.util.List;

public record AllQuestionneiresDTO(
        List<People> data
) {
}
