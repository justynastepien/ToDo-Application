package org.example.todoapp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//@MappedSuperclass // mapowalna superklasa która pozwala zamodelować na bazie
@Embeddable // gdy dziedziczenie nie jest najlepszym pomysłem
/*abstract*/ class BaseAuditableEntity {

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
        // operacja służaca do insertów, przy tworzeniu
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
        //merguje
    void preMerge(){
        updatedOn = LocalDateTime.now();
    }
}
