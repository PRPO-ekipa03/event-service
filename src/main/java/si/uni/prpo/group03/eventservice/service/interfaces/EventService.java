package si.uni.prpo.group03.eventservice.service.interfaces;

import si.uni.prpo.group03.eventservice.dto.EventCreateDTO;
import si.uni.prpo.group03.eventservice.dto.EventResponseDTO;
import si.uni.prpo.group03.eventservice.dto.EventUpdateDTO;

import java.util.List;

public interface EventService {
    EventResponseDTO createEvent(EventCreateDTO eventCreateDTO, Long userId);
    EventResponseDTO updateEvent(Long eventId, EventUpdateDTO eventUpdateDTO);
    void deleteEvent(Long eventId);
    EventResponseDTO getEventById(Long eventId);
    List<EventResponseDTO> getEventsByUserId(Long userId);
    List<EventResponseDTO> getUpcomingEvents();
    EventResponseDTO createEventWithReservation(EventCreateDTO eventCreateDTO, Long userId);

}
