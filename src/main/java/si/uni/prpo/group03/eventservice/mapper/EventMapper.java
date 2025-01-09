package si.uni.prpo.group03.eventservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import si.uni.prpo.group03.eventservice.dto.EventCreateDTO;
import si.uni.prpo.group03.eventservice.dto.EventResponseDTO;
import si.uni.prpo.group03.eventservice.dto.EventUpdateDTO;
import si.uni.prpo.group03.eventservice.model.Event;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    // Mapping from DTO to Entity for creation  
    @Mapping(target = "id", ignore = true) // Id is added in the service layer
    @Mapping(target = "userId", ignore = true) // Set userId manually in the service layer
    Event toEntity(EventCreateDTO eventCreateDTO);

    // Mapping from Update DTO to Entity (for partial updates)
    @Mapping(target = "id", ignore = true)          // Ignore id, as it shouldn't be updated
    @Mapping(target = "userId", ignore = true)      // Ignore userId, handled elsewhere
    @Mapping(target = "venueId", ignore = true)     // Ignore venueId, handled elsewhere
    void updateEntityFromDto(EventUpdateDTO eventUpdateDTO, @MappingTarget Event event);

    // Mapping from Entity to Response DTO
    @Mapping(target = "userName", ignore = true)      // Ignore userId, handled elsewhere
    @Mapping(target = "venueName", ignore = true)     // Ignore venueId, handled elsewhere
    @Mapping(target = "reservationId", ignore = true)   // Ignore, is set separately if needed
    EventResponseDTO toResponseDTO(Event event);
}
