package wanderhub.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanderhub.server.domain.member.dto.MemberDto;
import wanderhub.server.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

//    @PatchMapping("/update")
//    public ResponseEntity update(MemberDto.Patch patchMember) {
//         멤버를 찾아온다.
//         찾아온 멤버와 변경정보를 가진 Dto를 멤버로 변환한 멤버를 서비스 patchMember에 넣는다.
//        memberService.patchMember()
//         값을 반환한다.

}
