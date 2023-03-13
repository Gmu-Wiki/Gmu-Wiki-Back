package mpersand.Gmuwiki.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.ModifyNoticeRequest;
import mpersand.Gmuwiki.domain.user.entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    public void modifyPost(ModifyNoticeRequest modifyNoticeRequest) {
        this.title = modifyNoticeRequest.getTitle();
        this.content = modifyNoticeRequest.getContent();
    }
}
