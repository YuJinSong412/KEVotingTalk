package server.userdb;


public class User {

  private String uname;

  private String uemail;

  private String password;

  public User(String uname, String uemail, String password) {

    this.uname = uname;
    this.uemail = uemail;
    this.password = password;
  }
                

  public String getUname() {

    return uname;
  }

  public void setUname(String uname) {

    this.uname = uname;
  }

  public String getUemail() {

    return uemail;
  }

  public void setUemail(String uemail) {

    this.uemail = uemail;
  }

  public String getPassword() {

    return password;
  }

  public void setPassword(String password) {

    this.password = password;
  }

}
