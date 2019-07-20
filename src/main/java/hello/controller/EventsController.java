package hello.controller;

import hello.EventNotFoundException;
import hello.model.Event;
import hello.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.util.EventReaderDelegate;

@RestController
public class EventsController {

    @Autowired
    EventsRepository eventsRepository;

    @GetMapping("/events")
    public List<Event> fetchEvents() {
        return eventsRepository.findAll();
    }
          
    @PostMapping("/events")
    public Event addEvent (@RequestBody Event event) {
    	eventsRepository.save(event);
    	return event;
    }
    
    @GetMapping ("/events/{id}")
    public Event viewEvent(@PathVariable Long id) throws EventNotFoundException {
    	Optional <Event> event = eventsRepository.findById(id);
    	
    	if (!event.isPresent())
    		throw new EventNotFoundException();
    	
    	return eventsRepository.findById(id).get();
    }
    
    @PutMapping ("/events/{id}")
    public ResponseEntity<Object> changeEvent (@PathVariable Long id, @RequestBody Event event) {
    	Optional <Event> eventOptional = eventsRepository.findById(id);
    	
    	if (!eventOptional.isPresent())
    		return ResponseEntity.notFound().build();
    	
    	event.setId(id);
    	
    	eventsRepository.save(event);
    	
    	return ResponseEntity.noContent().build();  	    
    }
    
    @DeleteMapping ("/events/{id}")
    public void deleteEvent (@PathVariable Long id) {
    	Optional <Event> event = eventsRepository.findById(id);
    	
    	if (!event.isPresent())
    		throw new EventNotFoundException();
    	
    	eventsRepository.deleteById(id);
    }
    
    
}

