package si.uni.prpo.group03.eventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import si.uni.prpo.group03.eventservice.model.Event.EventStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventCreateDTO {

    @NotBlank(message = "Event name is required")
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 200)
    private String location;

    @NotNull(message = "Event date is required")
    private Timestamp eventDate; // Changed to Timestamp for date handling

    @NotNull(message = "Venue ID is required")
    private Long venueId;

    @NotBlank(message = "Status must be given")
    private EventStatus status; // Enum for status without JPA annotations

    @NotNull(message = "Guest IDs cannot be null")
    private List<Long> guestIds = new ArrayList<>(); // Optional: Ensure no duplicates in service layer

    // Getters and Setters
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
