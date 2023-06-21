package wanderhub.server.domain.accompany.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-21T20:21:50+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class AccompanyMapperImpl implements AccompanyMapper {

    @Override
    public AccompanyResponseDto toDto(Accompany entity) {
        if ( entity == null ) {
            return null;
        }

        AccompanyResponseDto.AccompanyResponseDtoBuilder accompanyResponseDto = AccompanyResponseDto.builder();

        accompanyResponseDto.id( entity.getId() );
        accompanyResponseDto.nickname( entity.getNickname() );
        accompanyResponseDto.accompanyLocal( entity.getAccompanyLocal() );
        accompanyResponseDto.accompanyDate( entity.getAccompanyDate() );
        accompanyResponseDto.maxNum( entity.getMaxNum() );
        accompanyResponseDto.accompanyTitle( entity.getAccompanyTitle() );
        accompanyResponseDto.accompanyContent( entity.getAccompanyContent() );
        accompanyResponseDto.openStatus( entity.isOpenStatus() );
        accompanyResponseDto.coordX( entity.getCoordX() );
        accompanyResponseDto.coordY( entity.getCoordY() );
        accompanyResponseDto.placeTitle( entity.getPlaceTitle() );
        accompanyResponseDto.registeredMembers( entity.getRegisteredMembers() );
        accompanyResponseDto.createdAt( entity.getCreatedAt() );
        accompanyResponseDto.modifiedAt( entity.getModifiedAt() );

        return accompanyResponseDto.build();
    }

    @Override
    public List<AccompanyResponseDto> toDtoList(List<Accompany> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AccompanyResponseDto> list = new ArrayList<AccompanyResponseDto>( entityList.size() );
        for ( Accompany accompany : entityList ) {
            list.add( toDto( accompany ) );
        }

        return list;
    }

    @Override
    public AccompanyDto toRequestDto(Accompany entity) {
        if ( entity == null ) {
            return null;
        }

        AccompanyDto.AccompanyDtoBuilder accompanyDto = AccompanyDto.builder();

        accompanyDto.accompanyLocal( entity.getAccompanyLocal() );
        if ( entity.getAccompanyDate() != null ) {
            accompanyDto.accompanyDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getAccompanyDate() ) );
        }
        accompanyDto.maxNum( entity.getMaxNum() );
        accompanyDto.accompanyTitle( entity.getAccompanyTitle() );
        accompanyDto.accompanyContent( entity.getAccompanyContent() );
        accompanyDto.coordX( entity.getCoordX() );
        accompanyDto.coordY( entity.getCoordY() );
        accompanyDto.placeTitle( entity.getPlaceTitle() );

        return accompanyDto.build();
    }

    @Override
    public List<AccompanyDto> toRequestDtoList(List<Accompany> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AccompanyDto> list = new ArrayList<AccompanyDto>( entityList.size() );
        for ( Accompany accompany : entityList ) {
            list.add( toRequestDto( accompany ) );
        }

        return list;
    }

    @Override
    public Accompany toEntity(AccompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Accompany.AccompanyBuilder accompany = Accompany.builder();

        accompany.accompanyLocal( dto.getAccompanyLocal() );
        if ( dto.getAccompanyDate() != null ) {
            accompany.accompanyDate( LocalDate.parse( dto.getAccompanyDate() ) );
        }
        accompany.maxNum( dto.getMaxNum() );
        accompany.accompanyTitle( dto.getAccompanyTitle() );
        accompany.accompanyContent( dto.getAccompanyContent() );
        accompany.coordX( dto.getCoordX() );
        accompany.coordY( dto.getCoordY() );
        accompany.placeTitle( dto.getPlaceTitle() );

        return accompany.build();
    }

    @Override
    public List<Accompany> toEntityList(List<AccompanyDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Accompany> list = new ArrayList<Accompany>( dtoList.size() );
        for ( AccompanyDto accompanyDto : dtoList ) {
            list.add( toEntity( accompanyDto ) );
        }

        return list;
    }

    @Override
    public Accompany fromResponseDtotoEntity(AccompanyResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Accompany.AccompanyBuilder accompany = Accompany.builder();

        accompany.id( dto.getId() );
        accompany.nickname( dto.getNickname() );
        accompany.accompanyLocal( dto.getAccompanyLocal() );
        accompany.accompanyDate( dto.getAccompanyDate() );
        accompany.maxNum( dto.getMaxNum() );
        accompany.accompanyTitle( dto.getAccompanyTitle() );
        accompany.accompanyContent( dto.getAccompanyContent() );
        accompany.openStatus( dto.isOpenStatus() );
        accompany.coordX( dto.getCoordX() );
        accompany.coordY( dto.getCoordY() );
        accompany.placeTitle( dto.getPlaceTitle() );
        accompany.registeredMembers( dto.getRegisteredMembers() );

        return accompany.build();
    }

    @Override
    public List<Accompany> fromResponseDtotoEntityList(List<AccompanyResponseDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Accompany> list = new ArrayList<Accompany>( dtoList.size() );
        for ( AccompanyResponseDto accompanyResponseDto : dtoList ) {
            list.add( fromResponseDtotoEntity( accompanyResponseDto ) );
        }

        return list;
    }
}
