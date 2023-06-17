package wanderhub.server.domain.member.entity;

import lombok.*;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", updatable = false)
    private Long Id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "EMAIL", length = 50, nullable = false, updatable = false)
    private String email;

    @Column(name = "NICKNAME", length = 50, updatable = false, unique = true)
    private String nickName;

    @Lob
    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "LOCAL", length = 16)
    private String local;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER) // N + 1 일부터 마주치기 위해서 EAGER // 권한은 값이 하나 이상일 수 있기에 사용.
    @CollectionTable(name = "roles", joinColumns =
    @JoinColumn(name = "member_id")         // 일대다 관계로 JoinColum해줌.
    )
    private List<String> roles = new ArrayList<>();

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(name = "MEMBER_STATUS", length = 16)
    private MemberStatus memberStatus;


    public Member(String email) {   // 이메일로 멤버 생성
        this.email = email;
    }


}
