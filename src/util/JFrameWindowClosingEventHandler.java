package util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import client.frame.FriendListPanel;
import client.frame.IndexPanel;
import controller.Controller;

public class JFrameWindowClosingEventHandler extends WindowAdapter {

  private String frameName;

  public JFrameWindowClosingEventHandler(String frameName) {

    this.frameName = frameName;
  }

  public void windowClosing(WindowEvent e) {

    JFrame frame = (JFrame) e.getWindow();
    frame.dispose();
    
    Controller controller = Controller.getInstance();
    // 본인 채팅방일때
    if (controller.username.equals(frameName)) {
      IndexPanel.userProfileButton.setText(frameName);
    }
    // 친구 채팅방일때
    for (JButton j : FriendListPanel.friendButtons) {
      if (j.getText().contains(frameName)) {
        j.setText(frameName);
      }
    }
  }
}

