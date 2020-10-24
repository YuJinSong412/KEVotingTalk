package server;

import server.datacommunication.ServerHandler;

public class ServerLaunch {

  public static void main(String[] args) {
    
    ServerHandler serverHandler = new ServerHandler();
    serverHandler.startServer();

  }
 
}