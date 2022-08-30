package org.example.todoapp.model.projection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.todoapp.model.Task;

public class GroupTaskReadModel { //Task czytany w obrębie grupy

    GroupTaskReadModel(Task source){
        description = source.getDescription();
        done = source.isDone();
    }

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private boolean done;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private String description;
}
