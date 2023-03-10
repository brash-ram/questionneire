package ru.rsreu.questionnaire.dto;

import ru.rsreu.questionnaire.data.entity.Present;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaveQuestionnaireDTO {
    private Long id;

    private String name;

    private Boolean isCome;
    private Boolean transport;

    private Set<Present> presents = new HashSet<>();

    private List<String> satellites = new ArrayList<>();

    private List<String> alcohol = new ArrayList<>();
}
