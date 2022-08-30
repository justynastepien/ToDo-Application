package org.example.todoapp.model;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.net.ContentHandler;
import java.util.List;
import java.util.Optional;

public interface TaskRepository { //lista metod dostępnych jakich mogę używać
    List<Task> findAll();

    Page<Task> findAll(Pageable page);

    Optional<Task> findById(Integer id);

    boolean existsById(Integer id);

    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    Task save(Task entity);

    //@RestResource(path = "done", rel = "done")  path aby było wyświetlane jako done zamiast findByDoneIsTrue, rel (łącze) to aby podpowiadało
    List<Task> findByDone(@Param("state") boolean done); //parametr czy chcemy true czy false

}
