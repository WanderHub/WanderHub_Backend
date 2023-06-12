package wanderhub.server.domain.accompany.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import wanderhub.server.global.audit.Auditable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="accompany")
public class Accompany extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOMPANY_ID")
    private Long id;

    @Column(name="MEMBER_ID", nullable=false)
    private Long memberId;

    @Column(name="WRITER_NAME", nullable=false)
    private String writerName;

    @Column(name="LOCAL", nullable=false)
    private String accompanyLocal;

    @Column(name="ACCOMPANY_DATE")
    private LocalDate accompanyDate;

    @Column(name="MAX_NUM", nullable=false)
    private int maxNum;

    @Column(name="TITLE", nullable=false)
    private String accompanyTitle;

    @Column(name="CONTENT", nullable=false)
    private String accompanyContent;

    @Column(name="STATUS", columnDefinition = "boolean default true")
    private boolean openStatus=true;

}
