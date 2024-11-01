package si.uni.prpo.group03.eventservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import java.sql.Timestamp;

@Entity
@Table(name = "events", indexes = {
    @Index(name = "idx_event_date", columnList = "eventDate"),
    @Index(name = "idx_user_id", columnList = "userId")
})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Event must be named")
    @Size(max = 100)
    private String name;

    @Column(nullable = true, unique = false)
    @Size(max = 2000)
    private String description;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Location must be given")
    @Size(max = 200)
    private String location;

    @Column(nullable = false)
    @NotNull(message = "Date must be given")
    private Timestamp eventDate;

    @Column(nullable = false)
    @NotNull(message = "Id of the creator must exist")
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "An event must have a venue")
    private Long venueId;

    @Enumerated(EnumType.STRING) // Store enum as a String in the database
    @Column(nullable = false)
    @NotNull(message = "Status must be given")
    private EventStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "event_guest_ids", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "guest_id")
    private List<Long> guestIds; // List of guest IDs (invitations are handled separately),maybe not emais but IDs?

    public enum EventStatus {
        UPCOMING,
        COMPLETED,
        CANCELED
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<Long> getGuestIds() {
        return guestIds;
    }

    public void setGuestIds(List<Long> guestIds) {
        this.guestIds = guestIds;
    }
}


//UTC