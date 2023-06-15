package wanderhub.server.domain.member.mapper;

import org.mapstruct.Mapper;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default Member patchMemberToMember(MemberDto.Patch patchMember) {
        return Member.builder()
                        .name(patchMember.getName())
                        .nickName(patchMember.getName())
                        .imgUrl(patchMember.getImgUrl())
                        .local(patchMember.getLocal())
                        .build();

    }

    default MemberDto.Response memberToMemberResponse(Member member) {
        return MemberDto.Response.builder()
                .Id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .displayName(member.getNickName())
                .imgUrl(member.getImgUrl())
                .local(member.getLocal())
                .memberStatus(member.getMemberStatus())
                .build();
    }
}
