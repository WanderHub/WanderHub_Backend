package wanderhub.server.domain.member.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-20T14:50:43+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberDtoPatchToMember(MemberDto.Patch memberDtoPatch) {
        if ( memberDtoPatch == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.name( memberDtoPatch.getName() );
        member.nickName( memberDtoPatch.getNickName() );
        member.imgUrl( memberDtoPatch.getImgUrl() );
        member.local( memberDtoPatch.getLocal() );

        return member.build();
    }
}
