package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.data.jpa.PeopleRepository;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public void persistPeople(String name) {
        peopleRepository.save(new People().setName(name));
    }
}
