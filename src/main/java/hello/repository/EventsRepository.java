package hello.repository;

import hello.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;



import java.util.ArrayList;
import java.util.List;


/*
 * JpaRepository è una superclasse che estende la CrudRepository ed altre sotto classi  
 */

public interface EventsRepository extends JpaRepository<Event, Long> {
    List<Event> findByName(@Param("name") String name);
    List<Event> findById (@Param ("id") String id);
}
