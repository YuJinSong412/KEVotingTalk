package server.datacommunication;

import java.io.Serializable;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class Message implements Serializable {

  private String sendUserName;

  private String sendComment;

  private LocalTime sendTime;

  private String messageType;

  private String receiveFriendName;

  public Message(String sendUserName, String sendComment, LocalTime sendTime, String messageType,
      String receiveFriendName) {

    this.sendUserName = sendUserName;
    this.sendComment = sendComment;
    this.sendTime = sendTime;
    this.messageType = messageType;
    this.receiveFriendName = receiveFriendName;
  }

  public String getSendUserName() {

    return sendUserName;
  }

  public void setSendUserName(String sendUserName) {

    this.sendUserName = sendUserName;
  }

  public String getSendComment() {

    return sendComment;
  }

  public void setSendComment(String sendComment) {

    this.sendComment = sendComment;
  }

  public LocalTime getSendTime() {

    return sendTime;
  }

  public void setSendTime(LocalTime sendTime) {

    this.sendTime = sendTime;
  }

  public String getMessageType() {

    return messageType;
  }

  public void setMessageType(String messageType) {

    this.messageType = messageType;
  }

  public String getReceiveFriendName() {

    return receiveFriendName;
  }

  public void setReceiveFriendName(String receiveFriendName) {

    this.receiveFriendName = receiveFriendName;
  }
}
