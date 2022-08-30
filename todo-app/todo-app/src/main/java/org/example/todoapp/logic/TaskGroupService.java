package org.example.todoapp.logic;

import org.example.todoapp.TaskConfigurationProperties;
import org.example.todoapp.model.TaskGroup;
import org.example.todoapp.model.TaskGroupRepository;
import org.example.todoapp.model.TaskRepository;
import org.example.todoapp.model.projection.GroupReadModel;
import org.example.todoapp.model.projection.GroupWriteModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;

@Service //coś pośrednie pomiędzy Controllerem a repozytorium
@RequestScope
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    TaskGroupService(final TaskGroupRepository repository, final TaskRepository taskRepository){

        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId){ //zamykamy grupę
        if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all the tasks firs");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(()->new IllegalArgumentException("TaskGroup with given id not found"));

        result.setDone(!result.isDone());
    }
}

