package wanderhub.server.domain.accompany.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.service.MemberService;
import wanderhub.server.global.exception.CustomLogicException;
import wanderhub.server.global.exception.ExceptionCode;

import javax.swing.text.html.Option;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccompanyServiceImpl implements AccompanyService {

    private final AccompanyRepository accompanyRepository;
    private final MemberService memberService;

    @Override
    public Accompany createAccompany(Accompany accompany, String userEmail) {
        Member member = memberService.findMember(userEmail);
        memberService.verificationMember(member); //회원 검증
        accompany.setNickname(member.getNickName());
        accompany.setOpenStatus(true);
        return accompanyRepository.save(accompany);
    }

    @Override
    public List<Accompany> findAll() {
        return accompanyRepository.findAll();
    }
//    @Override
//    public List<Accompany> findAll(Pageable pageable) {
//        List<Accompany> list = accompanyRepository.findAll(pageable).getContent();
//        return list;
//    }
//    @Override
//    public Page<Accompany> findAll(Pageable pageable) {
//        return accompanyRepository.findAll(pageable);
//    }

    @Override
    public Optional<Accompany> findById(Long id) {
        Optional<Accompany> accompany = accompanyRepository.findById(id);
        if(accompany.isPresent()) {
            return accompany;
        } else {
            throw new CustomLogicException(ExceptionCode.ACCOMPANY_NOT_FOUND);
        }
    }

    @Override
    public List<Accompany> findByLocal(String accompanyLocal) {
        return accompanyRepository.findByAccompanyLocal(accompanyLocal);
    }
//    @Override
//    public List<Accompany> findByLocal(String accompanyLocal, Pageable pageable) {
//        return accompanyRepository.findByAccompanyLocal(accompanyLocal, pageable);//.getContent();
//    }

    @Override
    public List<Accompany> findByDate(String accompanyDate) {
        return accompanyRepository.findByAccompanyDate(LocalDate.parse(accompanyDate));
    }

    @Override
    public List<Accompany> findByLocalAndDate(String accompanyLocal, String accompanyDate) {
        return accompanyRepository.findByAccompanyLocalAndAccompanyDate(accompanyLocal, LocalDate.parse(accompanyDate));
    }

    @Override
    public void deleteAccompany(Long id, String userEmail) {
        Member member = memberService.findByEmail(userEmail).get();
        memberService.verificationMember(member); //회원 검증

        Accompany accompany = accompanyRepository.findById(id)
                .orElseThrow(()->new CustomLogicException(ExceptionCode.ACCOMPANY_NOT_FOUND));

        if(member.getNickName().equals(accompany.getNickname())) { //생성한 사람만 지울 수 있음
            accompanyRepository.delete(accompany);
        } else {
            throw new CustomLogicException(ExceptionCode.ACCOMPANY_WRITER_DIFFERENT);
        }
    }
}
