package wanderhub.server.domain.member.mapper;

import org.mapstruct.Mapper;
import wanderhub.server.domain.community.mapper.BoardMapper;
import wanderhub.server.domain.community.mapper.BoardMapperImpl;
import wanderhub.server.domain.community_comment.mapper.BoardCommentMapper;
import wanderhub.server.domain.community_comment.mapper.BoardCommentMapperImpl;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.global.utils.Local;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface MemberMapper {

    BoardMapper boardMapper = new BoardMapperImpl();
    BoardCommentMapper boardCommentMapper = new BoardCommentMapperImpl();

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

    // 멤버 수정시 나오는 응답들.
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
            response.newbie(member.getNewbie());
            response.createdAt(member.getCreatedAt());
            response.modifiedAt(member.getModifiedAt());
            return response.build();
        }
    }


    // 멤버 단일조회 마이페이지용
    default MemberDto.GetResponse getMemberToMemberResponse(Member member) {
        if(member == null) {
            return null;
        } else {
            MemberDto.GetResponse response = MemberDto.GetResponse.builder()
                            .name(member.getName())
                            .email(member.getEmail())
                            .nickName(member.getNickName())
                            .imgUrl(member.getImgUrl())
                            .local(member.getLocal().getLocal())
                            .memberStatus(member.getMemberStatus())
                            .newbie(member.getNewbie())
                            .createdAt(member.getCreatedAt())
                            .modifiedAt(member.getModifiedAt())
                            .build();
            if(member.getBoardList()!=null) {
                response.setBoardList(boardMapper.boardsToBoardDtoResponseList(member.getBoardList()));
            }
            if(member.getBoCommentList()!=null) {
                response.setBoCommentList(boardCommentMapper.boCommentsToBoCommentDtoResponseList(member.getBoCommentList()));
            }
           if(member.getAccompanyMembers()!=null) {
               response.setAccompanyMembers(accompanyMemberMapper.accompanyMemberToAccompanyMemberDtoResponseList(member.getAccompanyMembers()));
           }
            return response;


        }
    }

}
