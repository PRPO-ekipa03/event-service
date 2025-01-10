package si.uni.prpo.group03.eventservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import si.uni.prpo.group03.eventservice.model.Event.EventStatus;

import java.sql.Timestamp;
import java.util.List;

@Schema(description = "Response DTO representing event details including creator and venue information")
public class EventResponseDTO {

    @NotNull
    @Schema(description = "Unique identifier of the event", example = "1")
    private Long id;  // Event ID

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Name of the event", maxLength = 100, example = "Annual Conference")
    private String name;

    @Size(max = 2000)
    @Schema(description = "Detailed description of the event", maxLength = 2000, example = "This event will cover...")
    private String description;

    @NotBlank
    @Size(max = 200)
    @Schema(description = "Location where the event is held", maxLength = 200, example = "New York City")
    private String location;

    @NotNull
    @Schema(description = "Date and time of the event in UTC", example = "2023-08-15T14:30:00Z")
    private Timestamp eventDate;

    @NotNull
    @Schema(description = "User ID of the event creator", example = "42")
    private Long userId;  // Creator's user ID

    @Schema(description = "Associated reservation ID for the event, if any", example = "100")
    private Long reservationId;

    @Schema(description = "Name of the user who created the event", example = "John Doe")
    private String userName = null;  // Optional - Creator's name for display purposes

    @Schema(description = "ID of the venue where the event takes place", example = "101")
    private Long venueId;  // Venue ID

    @Schema(description = "Name of the venue for display purposes", example = "Grand Hall")
    private String venueName = null;  // Venue name for display

    @Schema(
        description = "Current status of the event",
        example = "UPCOMING",
        allowableValues = {"UPCOMING", "COMPLETED", "CANCELED"}
    )
    private EventStatus status;  // Event status (e.g., upcoming, completed, canceled)

    @Schema(description = "List of guest IDs invited to the event", example = "[1, 2, 3]")
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

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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
