package si.uni.prpo.group03.eventservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import si.uni.prpo.group03.eventservice.exception.VenueNotFoundException;
import si.uni.prpo.group03.eventservice.exception.VenueServiceException;

// PROTOTIP

@Component
public class VenueServiceClient {

    private static final String VENUE_SERVICE_URL = "http://venue-service/api/venues/";

    private final RestTemplate restTemplate;

    @Autowired
    public VenueServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void verifyVenueExists(Long venueId) {
        try {
            String url = VENUE_SERVICE_URL + venueId;
            restTemplate.getForEntity(url, Void.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new VenueNotFoundException("Venue with ID " + venueId + " not found.");
        } catch (Exception e) {
            throw new VenueServiceException("Failed to check venue existence", e);
        }
    }
}