package server.datacommunication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerHandler {

  ExecutorService executorService; // 스레드풀인 ExecutorService 선언

  ServerSocket serverSocket; // 클라이언트 연결 수락

  List<Client> connections = new Vector<Client>(); // 연결되어있는 클라이언트들

  public void startServer() {

    ExecutorService threadPool = new ThreadPoolExecutor(10, // 코어 스레드 개수
        100, // 최대 스레드 개수
        120L, // 놀고 있는 시간
        TimeUnit.SECONDS, // 놀고 있는 시간 단위
        new SynchronousQueue<Runnable>() // 작업 큐
    ); // 초기 스레드 개수 0개,
    executorService = threadPool;
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress(5000));
      System.out.println("서버 연결 기다림");
      // -> serverSocket 생성 및 포트 바인딩
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 연결을 수락하는 코드
    Runnable runnable = new Runnable() { // 수락 작업 생성

      @Override
      public void run() {

        while (true) {
          try {
            Socket socket = serverSocket.accept(); // 클라이언트 연결 수락, client와 통신할 socket 리
            System.out.println("연결 수락: " + socket.getRemoteSocketAddress() + ": "
                + Thread.currentThread().getName());
            Client client = new Client(socket); // 클라이언트 객체에 저장.
            connections.add(client);
            System.out.println("연결 개수: " + connections.size());
          } catch (IOException e) {
            if (!serverSocket.isClosed()) { // serverSocket이 닫혀있지 않을 경우
              stopServer();
            }
            break;
          }
        }
      }
    };
    executorService.submit(runnable); // 스레드풀에서 처리.
  }

  public void stopServer() {

    try {
      Iterator<Client> iterator = connections.iterator(); // 모든 socket 닫기.
      while (iterator.hasNext()) {
        Client client = iterator.next();
        client.socket.close();
        iterator.remove();
      }
      if (serverSocket != null && !serverSocket.isClosed()) { // ServerSocket 닫기.
        serverSocket.close();
      }
      if (executorService != null && !executorService.isShutdown()) { // ExecutorService 종료.
        executorService.shutdown();
      }
    } catch (Exception e) {
    }
  }

  class Client {

    Socket socket;

    String userName;

    Client(Socket socket) {

      this.socket = socket;
      receive();
    }

    // 클라이언트의 데이터를 받는 메소드
    void receive() {

      Runnable runnable = new Runnable() {

        @Override
        public void run() {

          byte[] byteArr = new byte[1024];
          try {
            while (true) {
              InputStream inputStream = socket.getInputStream(); // 입력 스트림 얻기.
              int readByteCount = inputStream.read(byteArr); // 데이터 받기, 배열에 저장, 바이트 수 리턴.
              // 더 이상 입력 스트림으로부터 바이트 읽을 수 없다면 -1 리턴.
              if (readByteCount == -1) {
                throw new IOException();
              }
              Message ms = toObject(byteArr, Message.class); // 역직렬
              System.out.println("요청처리: " + socket.getRemoteSocketAddress() + ": "
                  + Thread.currentThread().getName());
              userName = ms.getSendUserName();
              System.out.println(userName + "qq");
              send(byteArr); // 본인한테 보
              for (Client client : connections) { // 모든 클라이언트에게 보냄
                System.out.println(client.userName + "ss" + ms.getReceiveFriendName());
                if (client.userName != null) {
                  if (client.userName.equals(ms.getReceiveFriendName())
                      && !ms.getSendUserName().equals(ms.getReceiveFriendName())) {
                    client.send(byteArr);
                  }
                }
              }
            }
          } catch (Exception e) {
            try {
              connections.remove(Client.this);
              socket.close();
            } catch (IOException e2) {
            }
          }
        }
      };
      executorService.submit(runnable);
    }

    private Message toObject(byte[] byteArr, Class<Message> class1) {

      Object obj = null;
      try {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
        ObjectInputStream ois = new ObjectInputStream(bis);
        obj = ois.readObject();
      } catch (Exception e) {
      }
      return class1.cast(obj);
    }

    // 데이터를 보내는 메소드
    void send(byte[] bytes) {

      Runnable runnable = new Runnable() {

        @Override
        public void run() {

          try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            System.out.println("서버에서 데이터 보냄");
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
      executorService.submit(runnable);
    }
  }
}
