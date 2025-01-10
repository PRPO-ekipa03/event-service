package si.uni.prpo.group03.eventservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import si.uni.prpo.group03.eventservice.dto.CreateReservationDTO;
import si.uni.prpo.group03.eventservice.dto.ResponseReservationDTO;
import si.uni.prpo.group03.eventservice.exception.VenueNotFoundException;
import si.uni.prpo.group03.eventservice.exception.VenueServiceException;

@Component
public class VenueServiceClient {

    private final RestTemplate restTemplate;
    private final String venueServiceUrl;

    @Autowired
    public VenueServiceClient(RestTemplate restTemplate,
                              @Value("${venue.service.url}") String venueServiceUrl) {
        this.restTemplate = restTemplate;
        this.venueServiceUrl = venueServiceUrl;
    }

    public void verifyVenueExists(Long venueId) {
        try {
            String url = venueServiceUrl + venueId;
            restTemplate.getForEntity(url, Void.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new VenueNotFoundException("Venue with ID " + venueId + " not found.");
        } catch (Exception e) {
            throw new VenueServiceException("Failed to check venue existence", e);
        }
    }

    public ResponseReservationDTO createReservation(CreateReservationDTO reservationDTO) {
        try {
            String url = venueServiceUrl + "reservations";
            return restTemplate.postForObject(url, reservationDTO, ResponseReservationDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new VenueNotFoundException("Failed to create reservation. Venue not found.");
        } catch (Exception e) {
            throw new VenueServiceException("Failed to create reservation", e);
        }
    }
}
