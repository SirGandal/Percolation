import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class BmpToFile {

  public static void main(String[] args) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("src/resources/yoda-80.bmp"));
      int w = img.getWidth();
      int h = img.getHeight();
      int[][] arr = new int[w][h];

      Raster raster = img.getData();

      PrintWriter writer = new PrintWriter("src/resources/yoda-80.txt", "UTF-8");
      
      if (w <= h) {
        writer.println(w);
      } else {
        writer.println(h);
      }

      String s = "";
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
          arr[x][y] = raster.getSample(x, y, 0);
          if (arr[x][y] == 0) {
            // open, write the coordinates to file
            writer.println((y + 1) + " " + (x + 1));
          }
          s += arr[x][y];
        }
        System.out.println(s);
        s = "";
      }
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
