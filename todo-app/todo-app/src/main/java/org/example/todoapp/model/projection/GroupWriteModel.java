package org.example.todoapp.model.projection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.todoapp.model.TaskGroup;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private String description;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private Set<GroupTaskWriteModel> tasks;

    public TaskGroup toGroup(){
        var result = new TaskGroup();
        result.setDescription(description);
        result.setTasks(tasks.stream()
                .map(GroupTaskWriteModel::toTask)
                .collect(Collectors.toSet())
        );
        return result;
    }
}
