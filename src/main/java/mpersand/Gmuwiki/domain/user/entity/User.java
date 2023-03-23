package mpersand.Gmuwiki.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mpersand.Gmuwiki.domain.user.enums.Role;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public User updatePassword(String password) {
        return new User(
                this.userId,
                this.email,
                password,
                this.name,
                this.number,
                this.role
        );
    }
}
