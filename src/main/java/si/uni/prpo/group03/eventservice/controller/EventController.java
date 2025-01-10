package si.uni.prpo.group03.eventservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.uni.prpo.group03.eventservice.dto.EventCreateDTO;
import si.uni.prpo.group03.eventservice.dto.EventResponseDTO;
import si.uni.prpo.group03.eventservice.dto.EventUpdateDTO;
import si.uni.prpo.group03.eventservice.service.interfaces.EventService;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Events", description = "Controller for managing events")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Create a new event", description = "Creates a new event for the provided user ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Event created successfully", 
                     content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid event data", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestHeader("X-User-Id") String xUserId,
            @RequestBody @Valid EventCreateDTO eventCreateDTO) {
        Long userId = Long.parseLong(xUserId);
        EventResponseDTO createdEvent = eventService.createEvent(eventCreateDTO, userId);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Operation(summary = "Create a new event with reservation", 
               description = "Creates a new event with an associated reservation for the provided user ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Event with reservation created successfully", 
                     content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid event data", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/reservation")
    public ResponseEntity<EventResponseDTO> createEventWithReservation(
            @RequestHeader("X-User-Id") String xUserId,
            @RequestBody @Valid EventCreateDTO eventCreateDTO) {
        Long userId = Long.parseLong(xUserId);
        EventResponseDTO createdEvent = eventService.createEventWithReservation(eventCreateDTO, userId);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve event by ID", description = "Fetches the details of an event by its unique identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Event retrieved successfully", 
                     content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long eventId) {
        EventResponseDTO event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }

    @Operation(summary = "Update an event", description = "Updates the details of an existing event.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Event updated successfully", 
                     content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid event data", content = @Content),
        @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long eventId,
            @RequestBody @Valid EventUpdateDTO eventUpdateDTO) {
        EventResponseDTO updatedEvent = eventService.updateEvent(eventId, eventUpdateDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @Operation(summary = "Delete an event", description = "Deletes an event by its unique identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Event deleted successfully", content = @Content),
        @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retrieve events by user ID", description = "Fetches a list of events created by the specified user.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Events retrieved successfully", 
                     content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found or no events found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/users")
    public ResponseEntity<List<EventResponseDTO>> getEventsByUserId(
            @RequestHeader("X-User-Id") String xUserId) {
        Long userId = Long.parseLong(xUserId);
        List<EventResponseDTO> events = eventService.getEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }
}
