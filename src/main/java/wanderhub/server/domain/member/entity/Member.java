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
    @Column(name = "member_id")
    private Long Id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Setter
    @Column(length = 50)
    private String nickname;

    @Lob
    @Setter
    private String imgUrl;

    @Setter
    @Column(length = 16)
    private String local;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(length = 16)
    private MemberStatus memberStatus;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER) // N + 1 일부터 마주치기 위해서 EAGER // 권한은 값이 하나 이상일 수 있기에 사용.
    @CollectionTable(name = "roles", joinColumns =
        @JoinColumn(name = "member_id")         // 일대다 관계로 JoinColum해줌.
    )
    private List<String> roles = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "member")
    List<Accompany> accompanies = new ArrayList<>();

}
