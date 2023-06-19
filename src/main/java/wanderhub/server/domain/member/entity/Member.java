package wanderhub.server.domain.member.entity;

import lombok.;
import org.hibernate.annotations.ColumnDefault;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", updatable = false)
    private Long Id;

    @Column(name = "EMAIL", length = 50, nullable = false, updatable = false)
    private String email;

    @Setter
    @Column(name = "NAME", length = 50)
    private String name;


    @Setter
    @Column(name = "NICKNAME", length = 50, unique = true)
    private String nickName;

    @Lob
    @Column(name = "IMG_URL")
    private String imgUrl;

    @Setter
    @Column(name = "LOCAL", length = 16)
    private String local;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER) // N + 1 일부터 마주치기 위해서 EAGER // 권한은 값이 하나 이상일 수 있기에 사용.
    private List<String> roles = new ArrayList<>();

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(name = "MEMBER_STATUS", length = 16)
    private MemberStatus memberStatus;

    @Setter
    @ColumnDefault("false")
    @Column(name = "NEWBIE")
    private Boolean newbie;

    public Member(String email, Boolean newbie) {   // 이메일로 멤버 생성
        this.email = email;
        this.newbie = newbie;
    }

    @Builder
    public Member(String name, String nickName, String imgUrl, String local) {
        this.name = name;
        this.nickName = nickName;
        this.imgUrl = imgUrl;
        this.local = local;
    }
}
