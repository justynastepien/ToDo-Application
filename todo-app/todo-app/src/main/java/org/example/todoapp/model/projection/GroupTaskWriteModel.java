package org.example.todoapp.model.projection;

import java.time.LocalDateTime;
import lombok.*;
import org.example.todoapp.model.Task;

public class GroupTaskWriteModel {

    //DTO Data Transfer Object, mamy pewność że przy transferze danych nic nie zepsujemy
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private String description;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private LocalDateTime deadline;

    public Task toTask(){
        return new Task(description, deadline);
    }
}
