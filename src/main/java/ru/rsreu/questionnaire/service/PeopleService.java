package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.data.entity.Present;
import ru.rsreu.questionnaire.data.jpa.PeopleRepository;
import ru.rsreu.questionnaire.data.jpa.PresentRepository;
import ru.rsreu.questionnaire.dto.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PresentRepository presentRepository;

    private final Long ID_MONEY_PRESENT = 6L;

    public People persistPeople(String name) {
        return peopleRepository.save(new People().setName(name));
    }

    public DataForCreateQuestionnaireRequest getIdForNewQuestionnaire(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        People people = optionalPeople.orElseThrow();
        return new DataForCreateQuestionnaireRequest(
                people.getId()
        );
    }

    public DataForStartQuestionnaireResponse getDataForNewQuestionnaire(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        People people = optionalPeople.orElseThrow();
        List<Present> presentList = presentRepository.findAllByPeopleIsNull();
        return new DataForStartQuestionnaireResponse(
                people.getName(),
                presentList.stream().map(present -> new PresentResponse(
                        present.getId(),
                        present.getName(),
                        present.getFile(),
                        present.getDescription())).collect(Collectors.toList())
        );
    }

    public void saveQuestionnaire(SaveQuestionnaireRequest dto) throws Exception {
        Optional<People> optionalPeople = peopleRepository.findById(dto.id());
        People people = optionalPeople.orElseThrow();
        Present present = null;

        if (dto.presents() != null) {
            present = presentRepository.findById(dto.presents()).get();
            if (present!= null && present.getPeople() != null) throw new Exception("Present already taken");
        }

        People savedPeople = peopleRepository.save(new People()
                .setId(dto.id())
                .setName(people.getName())
                .setIsCome(dto.isCome())
                .setTransport(dto.transport())
                .setPresents(present)
                .setSatellites(dto.satellites())
                .setAlcohol(dto.alcohol())
        );

        if (present != null && !present.getName().equals("Деньги")) {
            presentRepository.save(present.setPeople(true));
        }
    }

    public void notCumMan(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        People people = optionalPeople.orElseThrow();
        peopleRepository.save(new People()
                .setId(id)
                .setName(people.getName())
                .setIsCome(false)
                .setTransport(null)
                .setPresents(null)
                .setSatellites(null)
                .setAlcohol(null)
        );
    }

    public AllQuestionneiresResponse getAllData() {
        return new AllQuestionneiresResponse(peopleRepository.findAll());
    }
}
