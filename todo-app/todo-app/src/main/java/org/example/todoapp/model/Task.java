package org.example.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
//@Getter(value = AccessLevel.PUBLIC)
//@Setter(value = AccessLevel.PUBLIC)
public class Task /*extends BaseAuditableEntity*/ {

    @Id
    @Getter(value = AccessLevel.PUBLIC)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    @NotBlank(message = "Task's description must not be empty")
    private String description;
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private boolean done;
    @Column()
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private LocalDateTime deadline;
    //@Column()
    //@Transient  - gdy nie chcemy tego zapisywać w bazie
//    @Getter(value = AccessLevel.PUBLIC)
//    @Setter(value = AccessLevel.PUBLIC)
    //private LocalDateTime createdOn;
//    @Column()
//    @Getter(value = AccessLevel.PUBLIC)
//    @Setter(value = AccessLevel.PUBLIC)
    //private LocalDateTime updatedOn;
    @Embedded
    private BaseAuditableEntity audit = new BaseAuditableEntity();

    @ManyToOne() //many tasks to one group //cascade bardzo przydatne, ale bardziej w grupie
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;

    public Task(){

    }

    public Task(String description, LocalDateTime deadline){
        this.description = description;
        this.deadline = deadline;
    }

    public void updateFrom(final Task source){
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }



}
