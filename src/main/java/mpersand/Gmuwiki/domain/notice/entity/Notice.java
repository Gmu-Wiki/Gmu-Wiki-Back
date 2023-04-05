package mpersand.Gmuwiki.domain.notice.entity;

import lombok.*;
import mpersand.Gmuwiki.domain.user.entity.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private LocalDateTime createdDate;

    private LocalDateTime editedDate;

}
