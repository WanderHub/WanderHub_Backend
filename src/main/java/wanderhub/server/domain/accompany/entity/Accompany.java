package wanderhub.server.domain.accompany.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accompany")
public class Accompany extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accompany_id")
    private Long id;

    @Column(name="member_id", nullable=false)
    private Long memberId;

    @Column(name="writer_name", nullable=false)
    private String writerName;

    @Column(name="local", nullable=false)
    private String accompanyLocal;

    @Column(name="max_num", nullable=false)
    private int maxNum;

    @Column(name="title", nullable=false)
    private String accompanyTitle;

    @Column(name="content", nullable=false)
    private String accompanyContent;

    @Column(name="status", columnDefinition = "boolean default true")
    private boolean openStatus=true;

}
