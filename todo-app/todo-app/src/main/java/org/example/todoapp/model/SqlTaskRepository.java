package org.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //mówimy springowi aby korzystał z tych metod http
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> { // <nazwa encji, typ id>,  TaskRepository aby wiadoo było że jest beanem uszczegółowieniem

    @Override
    @Query(nativeQuery = true, value = "select count(*) >0 from tasks where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
}
