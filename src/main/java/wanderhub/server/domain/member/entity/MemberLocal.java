package wanderhub.server.domain.member.entity;

import lombok.Getter;

public enum MemberLocal {
    SEOUL("서울"),
    JEJUDO("제주도"),
    GYEONGGI("경기도"),
    GANGWONDO("강원도"),
    BUSAN("부산"),
    ULSAN("울산"),
    POHANG("포항"),
    DAEGU("대구"),
    DAEJEON("대전"),
    GWANGJU("광주"),
    SEJONG("세종"),
    INCHEON("인천"),
    CHUNGCHEONGNAMDO("충청남도"),
    CHUNGCHEONGBUKDO("충청북도"),
    GYEONGSANGNAMDO("경상남도"),
    GYEONGSANGBUKDO("경상북도"),
    JEOLLANAMDO("전라남도"),
    JEOLLABUKDO("전라북도"),
    X("선택 없음");

    @Getter
    private String local;

    MemberLocal(String local) {
        this.local = local;
    }
}
