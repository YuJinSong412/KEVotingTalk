package client.frame; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import controller.Controller;
import enums.AlignEnum;
import server.datacommunication.Message;
import util.ColorSet;
import util.FileChooser;
import util.UseImageFile;

@SuppressWarnings("serial")
public class ChatWindowPanel extends JPanel {

  private String panelName;

  private JTextArea textArea;

  private JButton sendButton;

  private JButton imgFileButton;

  private JTextPane jtp;

  private StyledDocument document;

  private Image img = UseImageFile.getImage("resources\\folder.png");

  Controller controller;

  private static String userName;

  public ChatWindowPanel(ImageIcon imageIcon, String friendName) {

    controller = Controller.getInstance();
    userName = controller.username;

    panelName = friendName;
    setBackground(ColorSet.talkBackgroundColor);
    setLayout(null);
    showFriendInfo(imageIcon, friendName);
    writeMessageArea();
    showContentArea();
    imgFileButton = showImgFileButton();
    add(imgFileButton);
    imgFileButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        File file = FileChooser.showFile();
        textArea.setText(file.toString());
      }
    });
    
    
    sendButton = showSendButton();
    add(sendButton);
    sendButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        Controller controller = Controller.getInstance();

        String messageType = null;
        if (textArea.getText().contains(".jpg") || textArea.getText().contains(".png")
            || textArea.getText().contains(".JPG") || textArea.getText().contains(".PNG")) {
          messageType = "file";
        } else {
          messageType = "text";
        }
        Message message = null;
        if (messageType.equals("file")) {
          message = new Message(controller.username, textArea.getText(), LocalTime.now(),
              messageType, friendName);
        } else {
          message = new Message(controller.username, textArea.getText(), LocalTime.now(),
              messageType, friendName);
        }

        controller.clientSocket.send(message);
        textArea.setText("");
      }
    });
  }

  public void paint(Graphics g) {

    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    Line2D lin = new Line2D.Float(0, 81, 400, 81);
    g2.draw(lin);
    Graphics2D g3 = (Graphics2D) g;
    Line2D lin2 = new Line2D.Float(0, 458, 400, 458);
    g3.draw(lin2);
  }

  private void showFriendInfo(ImageIcon imageIcon, String friendName) {

    JLabel friendInfolabel = new JLabel(imageIcon);
    friendInfolabel.setOpaque(true);
    friendInfolabel.setText(friendName);
    friendInfolabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    friendInfolabel.setBounds(0, 0, 400, 80);
    friendInfolabel.setBackground(Color.WHITE);
    add(friendInfolabel);
  }

  private JButton showImgFileButton() {

    JButton imgFileButton = new JButton(new ImageIcon(img));
    imgFileButton.setBackground(ColorSet.talkBackgroundColor);
    Border emptyBorder2 = BorderFactory.createEmptyBorder();
    imgFileButton.setBorder(emptyBorder2);
    imgFileButton.setFocusPainted(false);
    imgFileButton.setBounds(0, 460, 60, 40);
    return imgFileButton;
  }

  private JButton showSendButton() {

    JButton sendButton = new JButton("전송");
    sendButton.setBackground(ColorSet.messageSendButtonColor);
    sendButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    sendButton.setFocusPainted(false);
    sendButton.setBounds(320, 500, 68, 65);
    return sendButton;
  }

  private void writeMessageArea() {

    textArea = new JTextArea(20, 20);
    JScrollPane scroller = new JScrollPane(textArea);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setBounds(0, 500, 321, 65);
    add(scroller);
  }

  private void showContentArea() {

    StyleContext context = new StyleContext();
    document = new DefaultStyledDocument(context);
    jtp = new JTextPane(document);
    jtp.setBackground(ColorSet.talkBackgroundColor);
    jtp.setEditable(false);
    JScrollPane scroller2 = new JScrollPane(jtp);
    scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller2.setBounds(0, 80, 389, 380);
    add(scroller2);
  }

  
  public static void displayComment(Message message) {
    
    for (ChatWindowPanel chatName : IndexPanel.chatPanelName) {
      // 오른쪽으로 출력.
      if (userName.equals(message.getSendUserName())
          && chatName.panelName.equals(message.getReceiveFriendName())) {
        chatName.textPrint(message.getSendTime().format(DateTimeFormatter.ofPattern("aHH:mm"))
            + "  <" + message.getSendUserName() + ">", AlignEnum.RIGHT);
        if (message.getMessageType().equals("file")) {
          chatName.imgPrint(message.getSendComment());
        } else {
          chatName.textPrint(message.getSendComment(), AlignEnum.RIGHT);
        }
      }
      
      // 왼쪽으로 출력.    
      //!chatName.panelName.equals(message.getReceiveFriendName()) -> 나와의 채팅은 왼쪽 출력되면 안되니까.
      if (chatName.panelName.equals(message.getSendUserName()) && !chatName.panelName.equals(message.getReceiveFriendName())) {
        chatName.textPrint(message.getSendTime().format(DateTimeFormatter.ofPattern("aHH:mm"))
            + "  <" + message.getSendUserName() + ">", AlignEnum.LEFT);
        if (message.getMessageType().equals("file")) {
          chatName.imgPrint(message.getSendComment());
        } else {
          chatName.textPrint(message.getSendComment(),AlignEnum.LEFT);
        }
      }
    }
    
    
  }


  private void imgPrint(String sendComment) {

    Image imgFile = UseImageFile.getImage(sendComment);
    Image imgResize = imgFile.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
    StyledDocument doc2 = (StyledDocument) jtp.getDocument();
    Style style2 = doc2.addStyle("StyleName", null);
    StyleConstants.setIcon(style2, new ImageIcon(imgResize));
    try {
      doc2.insertString(doc2.getLength(), "invisible text" + "\n", style2);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

  private void textPrint(String string, AlignEnum alignEnum) {

    try {
      document = jtp.getStyledDocument();
      SimpleAttributeSet sortMethod = new SimpleAttributeSet();
      
      if(alignEnum == AlignEnum.RIGHT) {
        StyleConstants.setAlignment(sortMethod, StyleConstants.ALIGN_RIGHT);   
      }else if (alignEnum == AlignEnum.LEFT) {
        StyleConstants.setAlignment(sortMethod, StyleConstants.ALIGN_LEFT);  
      }
      document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sortMethod, true);
      document.insertString(document.getLength(), string + "\n", sortMethod);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

}
