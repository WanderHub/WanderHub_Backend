//package wanderhub.server.domain.accompany_member.entity;
//
//import lombok.*;
//import wanderhub.server.domain.accompany.entity.Accompany;
//import wanderhub.server.domain.member.entity.Member;
//
//import javax.persistence.*;
//
//@Builder
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class AccompaniedMember {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Id
//    @JoinColumn(name = "accompany_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Accompany accompanyId;
//
//    @Id
//    @JoinColumn(name = "member_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member memberId;
//
//
//}
