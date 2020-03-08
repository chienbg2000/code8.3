import com.sun.jna.platform.win32.WinDef;
import file.XuLyFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Location {
    static ArrayList<Integer> xxx = new ArrayList<>();
    static ArrayList<Integer> yyy = new ArrayList<>();


    public static void saveLocation(){
        for (int i = 1 ; i< 60 ; i++){
            WinDef.POINT  point = null;
            try {
                point = DesktopWindows.getItemPosition(i);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            xxx.add((int)point.x);
            yyy.add((int)point.y);


        }
        XuLyFile.writeFile(xxx,"xxx");
        XuLyFile.writeFile(yyy,"yyy");


    }


    public static void main(String[] args) {
        saveLocation();

    }

}
