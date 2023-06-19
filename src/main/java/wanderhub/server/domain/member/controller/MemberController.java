package wanderhub.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.mapper.MemberMapper;
import wanderhub.server.domain.member.service.MemberService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;


    @PatchMapping
    public ResponseEntity update(Principal principal, @RequestBody MemberDto.Patch patch) {
        Member findMember = memberService.findMember(principal.getName());   // 이메일 정보로 사용자를 찾아온다.
        log.info("srcMember = {} ", findMember.getEmail());
        log.info("srcMember = {} ", findMember.getMemberStatus());
        log.info("srcMember = {} ", findMember.getRoles());
        log.info("patch = {} ", patch.getNickName());
        log.info("patch = {} ", patch.getLocal());
        log.info("patch = {} ", patch.getName());
        log.info("patch = {} ", patch.getImgUrl());
        // memberService의 updateMember를 통해 사용자의 정보를 수정한다.   // PatchDto를 mapper를 통해서 엔티티로 매핑한다.
        Member updatedMember = memberService.updateMember(findMember, mapper.patchMemberToMember(patch));
        return new ResponseEntity<>(mapper.memberToMemberResponse(updatedMember), HttpStatus.OK);
    }
}
