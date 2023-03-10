package ru.rsreu.questionnaire.dto;

import ru.rsreu.questionnaire.data.entity.Present;

import java.util.List;

public record DataForQuestionneireDTO(
        Long id,
        String name,
        List<Present> presents
){}
