package ru.rsreu.questionnaire.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "presents")
public class Present {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "present_id", nullable = false)
    private Long id;
    private String name;
    private String file;
    private String description;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;
}
