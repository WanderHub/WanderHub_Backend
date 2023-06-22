package wanderhub.server.domain.accompany_member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import wanderhub.server.domain.accompany_member.dto.AccompanyMemberResponseDto;
import wanderhub.server.domain.accompany_member.entity.AccompanyMember;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccompanyMemberMapper {

    AccompanyMemberMapper INSTANCE = Mappers.getMapper(AccompanyMemberMapper.class);

    //Entity -> ResponseDto
    AccompanyMemberResponseDto accompanyMemberToAccompanyMemberResponseDto(AccompanyMember entity);
    List<AccompanyMemberResponseDto> accompanyMemberToAccompanyMemberDtoResponseList(List<AccompanyMember> entityList);

}