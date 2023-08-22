package mpersand.Gmuwiki.domain.user.entity;

import lombok.*;
import mpersand.Gmuwiki.domain.user.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "student_name" , nullable = false)
    private String name;

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_num")
    private int classNum;

    @Column(name = "student_num")
    private int stuNum;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateRole(Role role) {
        this.role = role;
    }
}
