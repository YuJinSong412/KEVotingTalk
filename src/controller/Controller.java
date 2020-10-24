package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.datacommunication.ClientSocket;
import client.frame.ErrorMessagePanel;
import client.frame.IndexPanel;
import client.frame.MainPanel;
import server.userdb.User;
import server.userdb.UserDAO;

public class Controller {

  private static Controller singleton = new Controller();

  public String username = null;

  public ClientSocket clientSocket;

  UserDAO userDAO;


  private Controller() {

    clientSocket = new ClientSocket();

    userDAO = new UserDAO();

  }

  public static Controller getInstance() {

    return singleton;
  }

  public void insertDB(User user) {

    boolean isInsert = userDAO.insertDB(user);

    if (isInsert) {
      MainPanel mainPanel = new MainPanel(MainPanel.frame);
      MainPanel.frame.change(mainPanel);
      JOptionPane.showMessageDialog(mainPanel, "회원가입 성공!!!", "회원가입", JOptionPane.WARNING_MESSAGE);
    } else {
      ErrorMessagePanel errorPanel = new ErrorMessagePanel("회원가입");
      MainPanel.frame.change(errorPanel);
    }

  }

  public void findUser(ArrayList<JTextField> userInfos) {

   username = userDAO.findUser(userInfos);

    if (username != null) {
      IndexPanel indexPanel = new IndexPanel();
      MainPanel.frame.change(indexPanel);
      clientSocket.startClient();
      JOptionPane.showMessageDialog(indexPanel, "로그인 성공!!!", "로그인", JOptionPane.WARNING_MESSAGE);
    } else if (username == null) {
      ErrorMessagePanel err = new ErrorMessagePanel("로그인");
      MainPanel.frame.change(err);
    }
  }

  public ArrayList<String> friendList() {

    ArrayList<String> friends = new ArrayList<String>();
    friends = userDAO.friendList();

    return friends;
  }

}
