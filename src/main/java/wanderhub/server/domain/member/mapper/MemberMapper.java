package wanderhub.server.domain.member.mapper;

import org.mapstruct.Mapper;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.global.utils.Local;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default Member memberDtoPatchToMember(MemberDto.Patch memberDtoPatch) {
        if(memberDtoPatch == null) {
            return null;
        } else {
            Member patchMember = Member.builder()
                    .name(memberDtoPatch.getName())
                    .nickName(memberDtoPatch.getNickName())
                    .imgUrl(memberDtoPatch.getImgUrl())
                    .local(Local.findByLocal(memberDtoPatch.getLocal()))
                    .build();
            return patchMember;
        }
    }

    default MemberDto.Response memberToMemberResponse(Member member) {
        if(member == null) {
            return null;
        } else {
            MemberDto.Response.ResponseBuilder response = MemberDto.Response.builder();
            response.email(member.getEmail());
            response.name(member.getName());
            response.nickName(member.getNickName());
            response.imgUrl(member.getImgUrl());
            response.local(member.getLocal().getLocal());
            response.memberStatus(member.getMemberStatus());
            response.createdAt(member.getCreatedAt());
            response.modifiedAt(member.getModifiedAt());
            return response.build();
        }
    }
}
