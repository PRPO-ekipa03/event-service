package si.uni.prpo.group03.eventservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "events", indexes = {
    @Index(name = "idx_event_date", columnList = "eventDate"),
    @Index(name = "idx_user_id", columnList = "userId")
})
@Schema(description = "Event entity representing scheduled events with associated details.")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the event", example = "1")
    private Long id;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Event must be named")
    @Size(max = 100)
    @Schema(description = "Name of the event", example = "Annual Conference")
    private String name;

    @Column(nullable = true, unique = false)
    @Size(max = 2000)
    @Schema(description = "Detailed description of the event", example = "This event covers ...")
    private String description;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Location must be given")
    @Size(max = 200)
    @Schema(description = "Location of the event", example = "New York City")
    private String location;

    @Column(nullable = false)
    @NotNull(message = "Date must be given")
    @Schema(description = "Date and time of the event in UTC", example = "2023-08-15T14:30:00Z")
    private Timestamp eventDate;

    @Column(nullable = false)
    @NotNull(message = "Id of the creator must exist")
    @Schema(description = "User ID of the event creator", example = "42")
    private Long userId;

    @Column(nullable = true)
    @Schema(description = "Venue ID where the event takes place", example = "101")
    private Long venueId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status must be given")
    @Schema(
        description = "Current status of the event",
        example = "UPCOMING",
        allowableValues = {"UPCOMING", "COMPLETED", "CANCELED"}
    )
    private EventStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "event_guest_ids", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "guest_id")
    @Schema(description = "List of guest IDs invited to the event", example = "[1, 2, 3]")
    private List<Long> guestIds;

    public enum EventStatus {
        UPCOMING,
        COMPLETED,
        CANCELED
    }

    // Getters and Setters
    public Long getId() {
        return id;
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
