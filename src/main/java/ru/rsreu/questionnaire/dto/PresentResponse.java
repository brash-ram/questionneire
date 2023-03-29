package ru.rsreu.questionnaire.dto;

public record PresentResponse(
        Long id,
        String name,
        String file,
        String description
) {
}
