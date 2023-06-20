package wanderhub.server.domain.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.mapper.MemberMapper;
import wanderhub.server.domain.member.service.MemberService;
import wanderhub.server.global.response.MessageResponseDto;
import wanderhub.server.global.response.SingleResponse;

import java.security.Principal;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // 회원 정보 수정
    @PatchMapping
    public ResponseEntity update(Principal principal, @RequestBody MemberDto.Patch patch) {
        Member findMember = memberService.findMember(principal.getName());   // 이메일 정보로 사용자를 찾아온다.
        // memberService의 updateMember를 통해 사용자의 정보를 수정한다.   // PatchDto를 mapper를 통해서 엔티티로 매핑한다.
        Member updatedMember = memberService.updateMember(findMember, mapper.memberDtoPatchToMember(patch));
        return ResponseEntity.ok(new SingleResponse<>(mapper.memberToMemberResponse(updatedMember)));
    }

    // 멤버조회
    @GetMapping
    public ResponseEntity getMember(Principal principal) {
        Member member = memberService.getMember(principal.getName());
        return ResponseEntity.ok(new SingleResponse<>(mapper.memberToMemberResponse(member)));
    }


    // 회원 탈퇴를 휴면 상태로 !
    @PatchMapping("/quit")
    public ResponseEntity quitMember(Principal principal) {
        memberService.quitMember(principal.getName());
        return new ResponseEntity(new MessageResponseDto("회원이 휴면계정으로 변경되었습니다."), HttpStatus.NO_CONTENT);
    }

    // logOut
}
