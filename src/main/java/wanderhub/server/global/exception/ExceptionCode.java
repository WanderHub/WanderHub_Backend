package wanderhub.server.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    NICKNAME_REQUIRED(400,"Nickname is required"),
    NICKNAME_NOT_UPDATE(400, "Nickname is never update"),
    NICKNAME_DUPLICATED(404, "Nickname Duplicated"),

    MEMBER_ALREADY_HUMAN(400, "Member is already Human"),
    MEMBER_NOT_FOUND(404, "Member is not Found"),
    MEMBER_NOT_ACTIVE(404,"Member is not ACTIVE"),
    MEMBER_EXISTS(409, "Member exists"),

    BOARD_NOT_FOUND(404, "Board is not found"),
    BOARD_WRITER_NOT_DIFFERENT(404, "Writer Different");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
