package si.uni.prpo.group03.eventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import si.uni.prpo.group03.eventservice.model.Event.EventStatus;

import java.sql.Timestamp;
import java.util.List;

public class EventResponseDTO {

    @NotNull
    private Long id;              // Event ID

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotBlank
    @Size(max = 200)
    private String location;

    @NotNull
    private Timestamp eventDate;

    @NotNull
    private Long userId;          // Creator's user ID

    private String userName = null;      // Optional - Creator's name for display purposes

    private Long venueId;         // Venue ID

    private String venueName = null;     // Venue name for display

    private EventStatus status;        // Event status (e.g., upcoming, completed, canceled)

    private List<Long> guestIds;  // IDs of invited guests

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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
