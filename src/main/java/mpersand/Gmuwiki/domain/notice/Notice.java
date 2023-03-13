package mpersand.Gmuwiki.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import mpersand.Gmuwiki.domain.user.entity.User;

import javax.naming.Name;
import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name ="notice_title", nullable = false)
    private String title;

    @Column(name ="notice_content", nullable = false)
    private String content;

    @Column(name ="notice_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    public void modifyPost(ModifyNoticeRequest modifyNoticeRequest){
        this.title=modifyNoticeRequest.getTitle();
        this.content = modifyNoticeRequest.getContent();
    }



}
