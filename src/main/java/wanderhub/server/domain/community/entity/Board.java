package wanderhub.server.domain.community.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Board extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long boardId;

    @Column(name = "NICKNAME", length = 50)
    @Setter
    private String nickName;    // 작성자

    @Column(name = "TITLE", length = 100)
    @Setter
    private String title;

    @Lob
    @Column(name = "CONTENT")
    @Setter
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "LOCAL")
    @Setter
    private BoardLocal local;


    @Column(name = "VIEW_POINT")
    @Setter
    private Long viewPoint;

    // 좋아요 추가하면서 좋아요 수 넣기
//////

    @ManyToOne        // EAGER 기본값 N+1이 보고싶다..
    @JoinColumn(name = "MEMBER_ID")
    @Setter
    private Member member;


    // 댓글
//////
}
