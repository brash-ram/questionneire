package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.People;
import ru.rsreu.questionnaire.data.entity.Present;
import ru.rsreu.questionnaire.data.jpa.PeopleRepository;
import ru.rsreu.questionnaire.data.jpa.PresentRepository;
import ru.rsreu.questionnaire.dto.DataForQuestionnaireDTO;
import ru.rsreu.questionnaire.dto.PresentDTO;

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
                        present.getUrl(),
                        present.getDescription())).collect(Collectors.toList())
        );
    }
}
