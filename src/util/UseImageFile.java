package util;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class UseImageFile {

  public static Image img = null;

  public static Image getImage(String FilePath) {

    try {
      File sourceImage = new File(FilePath);
      img = ImageIO.read(sourceImage);
      return img;
    } catch (Exception e) {
      System.out.println("이미지 파일이 없습니다.");
      return null;
    }
  }
}
