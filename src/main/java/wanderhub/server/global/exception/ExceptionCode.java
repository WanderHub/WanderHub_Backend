package wanderhub.server.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_NOT_ACTIVE(404,"Member is not ACTIVE"),
    MEMBER_EXISTS(409, "Member exists");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
