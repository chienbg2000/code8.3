import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Image {
    private static String desktopPath = "C:\\Users\\User\\Desktop\\";
    private static final String path = "traitim.png";
    private static final File file = new File(path);
    public static Path newPath(int n){
        String a = "";
        for (int i =0; i<= n; i++){
            a += " ";
        }

        Path destination=Paths.get(desktopPath + a + ".png");
        return  destination;
    }


    public static void main(String[] args) throws IOException {
//        String desktopPath =System.getProperty("user.home") + "\\"+"Desktop";
//        desktopPath =  desktopPath.replace("\\","\\\\") + "\\\\"  ;
        Path source= Paths.get("traitim.png");

        for (int i =0 ; i< 121 ; i++){
            Files.copy(source, newPath(i));
        }


    }
}