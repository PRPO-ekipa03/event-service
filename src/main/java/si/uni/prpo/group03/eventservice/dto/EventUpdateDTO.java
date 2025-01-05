package si.uni.prpo.group03.eventservice.dto;

import jakarta.validation.constraints.Size;
import si.uni.prpo.group03.eventservice.model.Event.EventStatus;

import java.sql.Timestamp;
import java.util.List;

public class EventUpdateDTO {

    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 200)
    private String location;

    private Timestamp eventDate;       

    private EventStatus status;           

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

