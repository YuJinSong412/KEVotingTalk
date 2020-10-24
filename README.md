<h1>KEVotingTalk</h1>

* 자바 스윙을 이용한 소켓 채팅 프로그램
* 사용 기술 : JAVA
* 설명 :  1 대 1 채팅 KEVotingTalk입니다. GUI를 구현하기 위해 swing을 이용했고 서버와 클라이언트는 소켓 통신을 통해 연결됩니다. 마지막으로 회원관리를 위해 MySQL DB 사용했습니다. 
* 구현기능 
  - 회원가입 시 중복 체크 / 중복일 때 에러 페이지 이동
  - 로그인 시 회원 체크 / 회원이 아닐 때 에러 페이지 이동
  - 프로필 이미지: 자신은 고정, 친구들은 랜덤으로 표시
  - 대화하고 싶은 친구 창을 누르면 친구 이름 옆에 "대화 중..." 표시 / 채팅창이 열려있으면 다시 열릴 수 없도록 하고, 닫으면 대화 중 사라짐
  - 채팅창 왼쪽 아래 이미지 아이콘을 클릭시 이미지 파일 전송 가능
  - 자신은 오른쪽, 친구는 왼쪽으로 대화 출력
  - 실시간 1 대 1 채팅이 가능
<br>

* 클래스 설명
  - client.datacommunication.ClientSocket.java : 클라이언트 측 송수신 파일
  - client.frame.ChatWindowFrame.java : 채팅 창 파일
  - client.frame.ChatWindowPanel.java : 채팅 화면 파일
  - client.frame.ErrorMessagePanel.java : 에러 화면 파일
  - client.frame.FriendListPanel.java : 친구 목록 화면 파일
  - client.frame.IndexPanel.java : 메인 화면 파일
  - client.frame.JoinMembershipPanel.java : 회원가입 화면 파일
  - client.frame.LoginPanel.java : 로그인 화면 파일
  - client.frame.MainFrame.java : 애플리케이션 창 파일
  - client.frame.MainPanel.java : 애플리케이션 시작 화면 파일
  - client.ClientLaunch.java : 클라이언트 실행 파일
  - controller.Controller.java : java와 DAO 사이의 중간 역할 파일
  - enums.AlignEnum : 채팅창 화면 오른쪽, 왼쪽 정렬 상수 파일 
  - enums.CommonWord : 애플리케이션 상수 파일
  - server.datacommunication.Message.java : 메시지 정보 파일
  - server.datacommunication.ServerHandler.java : 서버 측의 송수신 파일
  - server.userdb.User.java : 사용자 정보 파일
  - server.userdb.UserDAO.java : DB의 데이터 접근 파일
  - server.userdb.userDB.sql : MySQL의 쿼리 파일
  - server.ServerLaunch.java : 서버 실행 파일
  - util.ColorSet.java : 애플리케이션 색깔 모음 파일
  - util.CommonPanel.java : 애플리케이션 기본 화면 파일
  - util.JFrameWindowClosingEventHandler.java : 창 닫는 이벤트 파일
  - util.MainPanelButton.java : 애프릴케이션 시작 버튼 파일
  - util.UseImageFile.java : 애플리케이션 이미지 파일
  - util.UserInfoPanel.java : 애플리케이션 이미지 파일
  - util.UserProfileButton.java : 친구 버튼 파일
<br>

*  참고 <br>
macOS mysql 설치 & eclipse java 연동 : https://song-yujin.tistory.com/5?category=815435  
-> 윈도우 환경에서도 비슷하다! 
블로그 업로드 : 
