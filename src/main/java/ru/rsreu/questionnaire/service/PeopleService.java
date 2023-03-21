package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.data.entity.Present;
import ru.rsreu.questionnaire.data.jpa.PeopleRepository;
import ru.rsreu.questionnaire.data.jpa.PresentRepository;
import ru.rsreu.questionnaire.dto.AllQuestionneiresDTO;
import ru.rsreu.questionnaire.dto.DataForQuestionnaireDTO;
import ru.rsreu.questionnaire.dto.PresentDTO;
import ru.rsreu.questionnaire.dto.SaveQuestionnaireDTO;

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

    public DataForQuestionnaireDTO getDataForNewQuestionnaire(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        People people = optionalPeople.orElseThrow();
        List<Present> presentList = presentRepository.findAllByPeopleIsNull();
        return new DataForQuestionnaireDTO(
                people.getId(),
                people.getName(),
                presentList.stream().map(present -> new PresentDTO(
                        present.getId(),
                        present.getName(),
                        present.getFile(),
                        present.getDescription())).collect(Collectors.toList())
        );
    }

    public void saveQuestionnaire(SaveQuestionnaireDTO dto) {
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

    public AllQuestionneiresDTO getAllData() {
        return new AllQuestionneiresDTO(peopleRepository.findAll());
    }
}
