package wanderhub.server.domain.accompany_member.entity;

import lombok.*;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.member.entity.Member;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccompanyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOMPANY_ID", updatable = false)
    private Accompany accompany;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", updatable = false)
    private Member member;


}
