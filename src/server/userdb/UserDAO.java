package server.userdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

public class UserDAO {

  private String driver = "com.mysql.cj.jdbc.Driver";

  private String jdbcurl = "jdbc:mysql://localhost:3306/yujinDB?serverTimezone=UTC";

  private Connection conn;

  private PreparedStatement pstmt;

  public String username = null;

  public void connect() {

    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(jdbcurl, "yujin", "1234");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {

    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean insertDB(User user) {

    connect();
    String sql = "insert into member_table values(?,?,?)";
    
    boolean isInsert = false;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user.getUname());
      pstmt.setString(2, user.getUemail());
      pstmt.setString(3, user.getPassword());
      pstmt.executeUpdate();
      
      isInsert = true;
 
    } catch (SQLException e) {
      isInsert = false;
    }
    disconnect();
    
    return isInsert;
    
  }

  public String findUser(ArrayList<JTextField> userInfos) {

    connect();
    String sql = "select uname from member_table where uemail=? and password=?";
    String uemail = userInfos.get(0).getText();
    String password = userInfos.get(1).getText();
    
    String uname = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, uemail);
      pstmt.setString(2, password);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uname = rs.getString("uname");
      }
      
      username = uname;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    disconnect();
    
    return username; 
  }

  public ArrayList<String> friendList() {

    String uemail = findUserEmail();
    connect();
    ArrayList<String> friends = new ArrayList<String>();
    String sql =
        "select m.uname from member_table m, friendList f where m.uemail = f.friendEmail and f.userEmail = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, uemail);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        friends.add(rs.getString("uname"));
      }
    } catch (SQLException e) {
    }
    disconnect();
    return friends;
  }

  private String findUserEmail() {

    connect();
    String sql = "select uemail from member_table where uname=?";
    String uemail = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uemail = rs.getString("uemail");
      }
    } catch (SQLException e) {
    }
    disconnect();
    return uemail;
  }
}
