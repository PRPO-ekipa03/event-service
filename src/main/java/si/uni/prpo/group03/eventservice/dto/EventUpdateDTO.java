package si.uni.prpo.group03.eventservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import si.uni.prpo.group03.eventservice.model.Event.EventStatus;
import java.sql.Timestamp;
import java.util.List;

@Schema(description = "DTO for updating an event's details")
public class EventUpdateDTO {

    @Size(max = 100)
    @Schema(description = "Updated name of the event", maxLength = 100, example = "Annual Tech Conference")
    private String name;

    @Size(max = 2000)
    @Schema(description = "Updated description of the event", maxLength = 2000, example = "A detailed description of the event...")
    private String description;

    @Size(max = 200)
    @Schema(description = "Updated location of the event", maxLength = 200, example = "New York City")
    private String location;

    @Schema(description = "Updated date and time of the event in UTC", example = "2023-09-01T10:00:00Z")
    private Timestamp eventDate;

    @Schema(
        description = "Updated status of the event",
        example = "UPCOMING",
        allowableValues = {"UPCOMING", "COMPLETED", "CANCELED"}
    )
    private EventStatus status;

    @Schema(description = "List of guest IDs invited to the event", example = "[1, 2, 3]")
    private List<Long> guestIds;      

    // Getters and setters
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
