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
    @Column(nullable = false)
    private Long id;
    private String name;
    private String url;
    private String description;
}
