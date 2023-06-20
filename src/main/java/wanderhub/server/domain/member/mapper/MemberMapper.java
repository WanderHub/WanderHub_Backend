package wanderhub.server.domain.member.mapper;

import org.mapstruct.Mapper;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberDtoPatchToMember(MemberDto.Patch memberDtoPatch);
    default MemberDto.Response memberToMemberResponse(Member member) {
        if(member == null) {
            return null;
        } else {
            MemberDto.Response.ResponseBuilder response = MemberDto.Response.builder();
            response.Id(member.getId());
            response.email(member.getEmail());
            response.name(member.getName());
            response.nickName(member.getNickName());
            response.imgUrl(member.getImgUrl());
            response.local(member.getLocal());
            response.memberStatus(member.getMemberStatus());
            response.createdAt(member.getCreatedAt());
            response.modifiedAt(member.getModifiedAt());
            return response.build();
        }
    }
}
