package ru.rsreu.questionnaire.dto;

import java.util.List;

public record DataForQuestionnaireDTO(
        Long id,
        String name,
        List<PresentDTO> presents
){}
