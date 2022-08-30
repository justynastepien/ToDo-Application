package org.example.todoapp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "task_groups")
//@Getter(value = AccessLevel.PUBLIC)
//@Setter(value = AccessLevel.PUBLIC)
public class TaskGroup /*extends BaseAuditableEntity*/ {

    @Id
    @Getter(value = AccessLevel.PUBLIC)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    @NotBlank(message = "Task group's description must not be empty")
    private String description;
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private boolean done;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)

    @OneToMany(cascade = CascadeType.ALL, /*fetch = FetchType.LAZY,*/ mappedBy = "group"/*takie jak w tasku obcy*/) // lazy to dociagany wtedy kiedy to jest potrzebne(dobre dla kolekcji), używa kaskada
    private Set<Task> tasks;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    public TaskGroup(){

    }



}
