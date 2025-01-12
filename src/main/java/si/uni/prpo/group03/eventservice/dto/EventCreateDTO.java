package si.uni.prpo.group03.eventservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import si.uni.prpo.group03.eventservice.model.Event.EventStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "DTO for creating a new event")
public class EventCreateDTO {

    @NotBlank(message = "Event name is required")
    @Size(max = 100)
    @Schema(description = "Name of the event", maxLength = 100, example = "Annual Conference")
    private String name;

    @Size(max = 2000)
    @Schema(description = "Detailed description of the event", maxLength = 2000,
            example = "This event will cover industry trends and networking opportunities.")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 200)
    @Schema(description = "Location where the event will take place", maxLength = 200, example = "San Francisco")
    private String location;

    @NotNull(message = "Event date is required")
    @Schema(description = "Date and time of the event in UTC", example = "2023-09-01T10:00:00Z")
    private Timestamp eventDate; // Changed to Timestamp for date handling

    @Schema(description = "ID of the venue for the event", example = "101")
    private Long venueId;

    @Schema(description = "Initial status of the event", 
            example = "UPCOMING", 
            allowableValues = {"UPCOMING", "COMPLETED", "CANCELED"})
    private EventStatus status; // Enum for status without JPA annotations

    @NotNull(message = "Guest IDs cannot be null")
    @Schema(description = "List of guest IDs invited to the event", example = "[1,2,3]")
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
