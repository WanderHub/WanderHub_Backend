package wanderhub.server.domain.accompany.mapper;

import org.mapstruct.factory.Mappers;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.entity.Accompany;

public interface AccompanyMapper {

    AccompanyMapper mapper = Mappers.getMapper(AccompanyMapper.class);

    //Entity -> Dto
    AccompanyDto toDto(Accompany accompany);

    //Dto -> Entity
    Accompany toEntity(AccompanyDto dto);

}


