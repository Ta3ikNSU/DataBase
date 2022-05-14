package ta3ikdb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ta3ikdb.DTO.AnnouncementDTO;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.entities.Announcement;
import ta3ikdb.entities.Car;

@Mapper
public interface DTOMapper {
    @Mapping(target = "announcementDTO", source = "announcement")
    CarDTO carToCarDto(Car car);
    AnnouncementDTO announcementToAnnouncementDTO(Announcement announcement);
}
