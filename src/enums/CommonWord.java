package enums;


public enum CommonWord {

  SIGN_UP_MEMBERSHIP("회원가입", 0),
  LOGIN("로그인", 1),
  NAME("이름", 2),
  EMAIL("이메일", 3),
  PASSWORD("비밀번호", 4),
  MYPROFILE("내 프로필", 5),
  FRIEND("친구", 6),
  GOBACK("뒤로가기",7);


  private final String text;

  private final int num;


  CommonWord(String text, int num) {

    this.text = text;
    this.num = num;
  }

  public String getText() {

    return text;
  }

  public int getNum() {

    return num;
  }


}
