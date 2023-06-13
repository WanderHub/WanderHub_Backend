package wanderhub.server.domain.accompany.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyDto.AccompanyDtoBuilder;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto.AccompanyResponseDtoBuilder;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.entity.Accompany.AccompanyBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T14:50:09+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class AccompanyMapperImpl implements AccompanyMapper {

    @Override
    public AccompanyResponseDto toDto(Accompany entity) {
        if ( entity == null ) {
            return null;
        }

        AccompanyResponseDtoBuilder accompanyResponseDto = AccompanyResponseDto.builder();

        accompanyResponseDto.id( entity.getId() );
        accompanyResponseDto.memberId( entity.getMemberId() );
        accompanyResponseDto.writerName( entity.getWriterName() );
        accompanyResponseDto.accompanyLocal( entity.getAccompanyLocal() );
        accompanyResponseDto.accompanyDate( entity.getAccompanyDate() );
        accompanyResponseDto.maxNum( entity.getMaxNum() );
        accompanyResponseDto.accompanyTitle( entity.getAccompanyTitle() );
        accompanyResponseDto.accompanyContent( entity.getAccompanyContent() );
        accompanyResponseDto.openStatus( entity.isOpenStatus() );
        accompanyResponseDto.createdAt( entity.getCreatedAt() );
        accompanyResponseDto.modifiedAt( entity.getModifiedAt() );

        return accompanyResponseDto.build();
    }

    @Override
    public AccompanyDto toRequestDto(Accompany entity) {
        if ( entity == null ) {
            return null;
        }

        AccompanyDtoBuilder accompanyDto = AccompanyDto.builder();

        accompanyDto.id( entity.getId() );
        accompanyDto.memberId( entity.getMemberId() );
        accompanyDto.writerName( entity.getWriterName() );
        accompanyDto.accompanyLocal( entity.getAccompanyLocal() );
        accompanyDto.accompanyDate( entity.getAccompanyDate() );
        accompanyDto.maxNum( entity.getMaxNum() );
        accompanyDto.accompanyTitle( entity.getAccompanyTitle() );
        accompanyDto.accompanyContent( entity.getAccompanyContent() );


        return accompanyDto.build();
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

        AccompanyBuilder accompany = Accompany.builder();

        accompany.id( dto.getId() );
        accompany.memberId( dto.getMemberId() );
        accompany.writerName( dto.getWriterName() );
        accompany.accompanyLocal( dto.getAccompanyLocal() );
        accompany.accompanyDate( dto.getAccompanyDate() );
        accompany.maxNum( dto.getMaxNum() );
        accompany.accompanyTitle( dto.getAccompanyTitle() );
        accompany.accompanyContent( dto.getAccompanyContent() );
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
}
