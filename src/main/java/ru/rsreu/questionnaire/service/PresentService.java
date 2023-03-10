package ru.rsreu.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.questionnaire.data.entity.Present;
import ru.rsreu.questionnaire.data.jpa.PresentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PresentService {
    private final PresentRepository presentRepository;

    public List<Present> getAvailablePresents() {
        return null;
    }
}
