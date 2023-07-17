package mpersand.Gmuwiki.domain.inquiry.entity;

import lombok.*;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;
import mpersand.Gmuwiki.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    @CreatedDate
    private LocalDateTime createdDate;
}
