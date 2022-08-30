package org.example.todoapp.controller;

import org.example.todoapp.model.Task;
import org.example.todoapp.model.TaskRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController //powiązanie klasy z istniejącym repozytorium, dorwanie się do controllera springa, zarządza repozytorium
//@RestController
public class TaskController {
    private final TaskRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    public TaskController(TaskRepository repository) { //odpali się przy uruchamianiu springa, repository bedzie istnieć
        this.repository = repository;
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/tasks")
    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"}) //Jeśli zostały użyte sort/page/size to wtedy normalny hateoas
    ResponseEntity<List<Task>> readAllTasks(){ //zamienia na jsona
        //logger.warn("Exposing all the task!");
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping(value = "/tasks")  //stronicowanie nie przez repo a przez kontroler
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        //logger.warn("Exposing all the task!");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PutMapping("/tasks/{id}") //słuchaj tu będzie jakieś id którym może się posłużymy
    ResponseEntity<?> updateTask(@PathVariable int id ,@RequestBody @Valid Task toUpdate) { // bierzymy z path zmienną,  bierzemy to co jest w ciele robimy obiekt javovy, musi przejść validację
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> {task.updateFrom(toUpdate);
                    repository.save(task); // lub @transactional
                });

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<?> readTask(@PathVariable int id){

        if(repository.findById(id).isPresent()){
            return ResponseEntity.ok(repository.findById(id));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate){
        Task result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @Transactional //ponieważ task sie zmienił to w commicie pójdzie z tą nową wartością
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }

}
