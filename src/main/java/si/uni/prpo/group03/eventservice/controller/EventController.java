package si.uni.prpo.group03.eventservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.uni.prpo.group03.eventservice.dto.EventCreateDTO;
import si.uni.prpo.group03.eventservice.dto.EventResponseDTO;
import si.uni.prpo.group03.eventservice.dto.EventUpdateDTO;
import si.uni.prpo.group03.eventservice.service.interfaces.EventService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody @Valid EventCreateDTO eventCreateDTO,
            @RequestParam(value = "userId", defaultValue = "1") Long userId) {
        EventResponseDTO createdEvent = eventService.createEvent(eventCreateDTO, userId);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long eventId) {
        EventResponseDTO event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long eventId,
            @RequestBody @Valid EventUpdateDTO eventUpdateDTO) {
        EventResponseDTO updatedEvent = eventService.updateEvent(eventId, eventUpdateDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<EventResponseDTO>> getEventsByUserId(@PathVariable Long userId) {
        List<EventResponseDTO> events = eventService.getEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }
}
