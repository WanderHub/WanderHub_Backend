package wanderhub.server.domain.accompany.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Accompany extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOMPANY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Setter
    @Column(name="NICKNAME", nullable=false)
    private String nickname;

    @Column(name="LOCAL", nullable=false)
    private String accompanyLocal;

    @Setter
    @Column(name="ACCOMPANY_DATE")
    private LocalDate accompanyDate;

    @Column(name="MAX_NUM", nullable=false)
    private int maxNum;

    @Column(name="TITLE", nullable=false)
    private String accompanyTitle;

    @Column(name="CONTENT", nullable=false)
    private String accompanyContent;

    @Setter
    @Column(name="STATUS", columnDefinition = "boolean default true")
    private boolean openStatus;

    @Column(name = "COORD_X")
    private double coordX;

    @Column(name = "COORD_Y")
    private double coordY;

    @Column(name = "PLACE_TITLE")
    private String placeTitle;

    @Formula("(select count(1) from accompany_member am where am.accompany_id = id)")
    private int registeredMembers;

}
