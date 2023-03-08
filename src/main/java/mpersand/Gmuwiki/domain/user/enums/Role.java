package mpersand.Gmuwiki.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    USER, ADMIN;
    @JsonCreator
    public static Role from(String s) {
        return Role.valueOf(s.toUpperCase());
    }
}
