package org.example.todoapp.model;


import javax.persistence.*;

import lombok.*;

import java.util.Set;

@Entity
@Table(name = "project_steps")
public class ProjectStep {

    @Id
    @Getter(AccessLevel.PUBLIC)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private String description;

    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private int daysToDeadline;

    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public ProjectStep(){

    }

}
