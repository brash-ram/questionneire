package ru.rsreu.questionnaire.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Accessors(chain = true)
@Table(name = "peoples")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String name;

    private Boolean isCome;
    private Boolean transport;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Present> presents = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> satellites = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> alcohol = new HashSet<>();
}
