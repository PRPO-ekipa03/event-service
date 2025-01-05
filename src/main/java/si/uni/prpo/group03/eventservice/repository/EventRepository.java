package si.uni.prpo.group03.eventservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import si.uni.prpo.group03.eventservice.model.Event;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by organizer
    List<Event> findByUserId(Long userId);

    // Find events by a specific date or within a date range
    List<Event> findByEventDate(Timestamp eventDate);
    List<Event> findByEventDateBetween(Timestamp startDate, Timestamp endDate);

    // Retrieves events by venue ID and date range
    @Query("SELECT e.eventDate FROM Event e WHERE e.venueId = :venueId AND e.eventDate BETWEEN :startDate AND :endDate")
    List<Timestamp> findUnavailableDatesByVenue(@Param("venueId") Long venueId,
                                                @Param("startDate") Timestamp startDate,
                                                @Param("endDate") Timestamp endDate);

    // Find events by venue
    List<Event> findByVenueId(Long venueId);

    // Find upcoming events
    List<Event> findByEventDateAfter(Timestamp currentDate);

    // Find events by invited guest
    @Query("SELECT e FROM Event e JOIN e.guestIds g WHERE g = :guestId")
    List<Event> findByGuestId(Long guestId);

    // Find events by location
    List<Event> findByLocationContaining(String location);

    // Paginated results for events by user or venue
    Page<Event> findByUserId(Long userId, Pageable pageable);
    Page<Event> findByVenueId(Long venueId, Pageable pageable);

    // Optional - Find by status
    List<Event> findByStatus(String status);
}
