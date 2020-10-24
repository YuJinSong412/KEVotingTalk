package client.frame;

import javax.swing.JPanel;
import enums.CommonWord;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.ColorSet;
import util.MainPanelButton;
import util.UseImageFile;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

  private Image img = UseImageFile.getImage("resources\\logoicon.png");

  private JLabel logoImgLabel;

  private MainPanelButton signUpButton;

  private MainPanelButton loginButton;
  
  public static MainFrame frame;

  public MainPanel(MainFrame frame) {

    MainPanel.frame = frame;
    setBackground(ColorSet.talkBackgroundColor);
    setLayout(null);
    showLogo();
    moveSignUpPanel();
    moveLogoPanel();
  }

  /*로고 아이콘*/
  private void showLogo() {

    logoImgLabel = new JLabel(new ImageIcon(img));
    logoImgLabel.setBounds(95, 90, 200, 200);
    add(logoImgLabel);
  }

  /*회원가입 버튼*/
  private void moveSignUpPanel() {

    signUpButton = new MainPanelButton(CommonWord.SIGN_UP_MEMBERSHIP.getText());
    signUpButton.setBounds(30, 370, 330, 35);
    signUpButton.setOpaque(true);
    add(signUpButton);
    signUpButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        JoinMembershipPanel joinMembershipPanel = new JoinMembershipPanel();
        MainPanel.frame.change(joinMembershipPanel);
      }
    });
  }

  /*로그인 버튼*/
  private void moveLogoPanel() {

    loginButton = new MainPanelButton(CommonWord.LOGIN.getText());
    loginButton.setBounds(30, 420, 330, 35);
    loginButton.setOpaque(true);
    add(loginButton);
    loginButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        LoginPanel loginPanel = new LoginPanel();
        MainPanel.frame.change(loginPanel);
      }
    });
  }
}
