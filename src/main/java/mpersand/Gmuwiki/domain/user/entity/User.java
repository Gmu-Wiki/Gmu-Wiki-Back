package mpersand.Gmuwiki.domain.user.entity;

import lombok.*;
import mpersand.Gmuwiki.domain.user.enums.Role;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "student_name" , nullable = false)
    private String name;

    @Column(name = "student_num" , nullable = false , unique = true , length = 4)
    private String number;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updatePassword(String password) {
        this.password = password;
    }
}
