package client.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

  private MainPanel mainPanel;

  public MainFrame() {

    setTitle("KevotingTalk");
    setBounds(800, 250, 400, 600);
    mainPanel = new MainPanel(this);
    getContentPane().add(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  /*panel 바꾸기*/
  public void change(JPanel panelName) {

    getContentPane().removeAll();
    getContentPane().add(panelName);
    revalidate();
    repaint();
  }
}
