package si.uni.prpo.group03.eventservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.uni.prpo.group03.eventservice.client.UserServiceClient;
import si.uni.prpo.group03.eventservice.client.VenueServiceClient;
import si.uni.prpo.group03.eventservice.dto.EventCreateDTO;
import si.uni.prpo.group03.eventservice.dto.EventResponseDTO;
import si.uni.prpo.group03.eventservice.dto.EventUpdateDTO;
import si.uni.prpo.group03.eventservice.exception.EventNotFoundException;
import si.uni.prpo.group03.eventservice.mapper.EventMapper;
import si.uni.prpo.group03.eventservice.model.Event;
import si.uni.prpo.group03.eventservice.repository.EventRepository;
import si.uni.prpo.group03.eventservice.service.interfaces.EventService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final VenueServiceClient venueServiceClient;
    private final UserServiceClient userServiceClient;
    // Constants for error messages
    private static final String EVENT_NOT_FOUND_PREFIX = "Event with ID ";
    private static final String NOT_FOUND_SUFFIX = " not found";

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, VenueServiceClient venueServiceClient, UserServiceClient userServiceClient) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.venueServiceClient = venueServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public EventResponseDTO createEvent(EventCreateDTO eventCreateDTO, Long userId) {
        // Map EventCreateDTO to Event entity
        Event event = eventMapper.toEntity(eventCreateDTO);
        event.setUserId(userId); // Set the user ID directly from the authenticated context
        Event savedEvent = eventRepository.save(event);

        // Map the saved Event entity to EventResponseDTO
        return eventMapper.toResponseDTO(savedEvent);
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, EventUpdateDTO eventUpdateDTO) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(EVENT_NOT_FOUND_PREFIX + eventId + NOT_FOUND_SUFFIX));

        // Use the mapper to apply non-null fields from EventUpdateDTO to the existing Event entity
        eventMapper.updateEntityFromDto(eventUpdateDTO, existingEvent);
        
        Event updatedEvent = eventRepository.save(existingEvent);
        
        // Map the updated Event entity to EventResponseDTO
        return eventMapper.toResponseDTO(updatedEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventNotFoundException(EVENT_NOT_FOUND_PREFIX + eventId + NOT_FOUND_SUFFIX);
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventResponseDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(EVENT_NOT_FOUND_PREFIX + eventId + NOT_FOUND_SUFFIX));
        return eventMapper.toResponseDTO(event);
    }

    @Override
    public List<EventResponseDTO> getEventsByUserId(Long userId) {
        // Verify user existence using UserServiceClient
        userServiceClient.verifyUserExists(userId);

        return eventRepository.findByUserId(userId).stream()
                .map(eventMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventResponseDTO> getUpcomingEvents() {
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        return eventRepository.findByEventDateAfter(currentTimestamp).stream()
                .map(eventMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
