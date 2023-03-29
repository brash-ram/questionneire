package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.data.entity.Present;
import ru.rsreu.questionnaire.data.jpa.PeopleRepository;
import ru.rsreu.questionnaire.data.jpa.PresentRepository;
import ru.rsreu.questionnaire.dto.AllQuestionneiresResponse;
import ru.rsreu.questionnaire.dto.DataForQuestionnaireRequest;
import ru.rsreu.questionnaire.dto.PresentResponse;
import ru.rsreu.questionnaire.dto.SaveQuestionnaireRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PresentRepository presentRepository;

    public People persistPeople(String name) {
        return peopleRepository.save(new People().setName(name));
    }

    public DataForQuestionnaireRequest getDataForNewQuestionnaire(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        People people = optionalPeople.orElseThrow();
        List<Present> presentList = presentRepository.findAllByPeopleIsNull();
        return new DataForQuestionnaireRequest(
                people.getId(),
                people.getName(),
                presentList.stream().map(present -> new PresentResponse(
                        present.getId(),
                        present.getName(),
                        present.getFile(),
                        present.getDescription())).collect(Collectors.toList())
        );
    }

    public void saveQuestionnaire(SaveQuestionnaireRequest dto) {
        People people = new People()
                .setId(dto.id())
                .setName(dto.name())
                .setIsCome(dto.isCome())
                .setTransport(dto.transport())
                .setPresents(new HashSet<>(presentRepository.findAllById(dto.presents())))
                .setSatellites(dto.satellites())
                .setAlcohol(dto.alcohol());
        peopleRepository.save(people);
    }

    public AllQuestionneiresResponse getAllData() {
        return new AllQuestionneiresResponse(peopleRepository.findAll());
    }
}
