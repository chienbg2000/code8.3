import file.XuLyFile;

import java.util.ArrayList;

public class AnimationStart {
    public static boolean folderV = true;
    public static int fl1 = 122;

    public static void clear(){
        for (int i = 1; i <Start.iconCount ; i++) {
            Start.iconList.get(i).setXY(Start.width/2,Start.height/2-50);
        }

        for (int i = 1; i <Start.iconCount ; i++) {
            Start.iconList.get(i).iconRunAndClearThread();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void momFoder(){
        new Thread(){
            public void run(){
                int lr=0;
                while(folderV){
                    int step = 0;
                    if(lr==0){
                        step = -10;
                        lr =1;
                    }
                    else {
                        step = +10;
                        lr = 0;
                    }
                    DesktopWindows.goToXY(Start.width/2 + step,Start.height/2,0);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    public static void set83(){
        ArrayList<Integer> xxx = (ArrayList<Integer>) XuLyFile.readFile("xxx");
        ArrayList<Integer> yyy = (ArrayList<Integer>) XuLyFile.readFile("yyy");

        for(int i = 1 ; i< 59; i++ ){
            int x = xxx.get(i);
            int y = yyy.get(i);

            Start.iconList.get(i).setXY(x,y);

        }
    }

    public static void run83(){
        set83();
        for (int i = 1; i <59 ; i++) {

            DesktopWindows.goToXY(Start.width/2,Start.height/2-50,i);
//            Start.iconList.get(i).setXY(100,100);
            Start.iconList.get(i).iconRunThread();

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        folderV = false;
    }

    public static void setFlower2(int size){
        for(int i = 1 ; i< 61; i++ ){

            double radians = Math.toRadians(i*6);
            int x =Start.width/2 - (int)(size *16*Math.pow(Math.sin(radians),3));
            int y =Start.height*2/5 - (int)(size *((13*Math.cos(radians)) - (5*Math.cos(2*radians)) - (2*Math.cos(3*radians))- (Math.cos(4*radians))));

            Start.iconList.get(i).setXY(x,y);

        }

        for(int i = 60 ; i< Start.iconCount; i++ ){

            double radians = Math.toRadians(i*12);
            int x =-1;
            int y =-1;

            Start.iconList.get(i).setXY(x,y);

        }




    }

    public static void runFlower2(){
        setFlower2(30);
        folderV = true;
        momFoder();
        for (int i = 1; i <60 ; i++) {

            DesktopWindows.goToXY(Start.width/2,Start.height/2-50,i);
//            Start.iconList.get(i).setXY(100,100);
            Start.iconList.get(i).iconRunThread();

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        folderV = false;
        DesktopWindows.goToXY(0,-1000,-1000);
        // xoay tron
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            setFlower2(28);

            for (int i = 1; i <60 ; i++) {
                Start.iconList.get(i).iconGoToXY();

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setFlower2(30);

            for (int i = 1; i <60 ; i++) {
                Start.iconList.get(i).iconGoToXY();

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }

    public static void setFlower1(){

        for(int i = 1 ; i< fl1; i++ ){
            double radians = Math.toRadians(i*6);
            int r = (int) (1*Math.cos(1.5*radians)*500);
            int x = (int) (Start.width/2 + r*Math.cos(radians));
            int y = (int) ((int) (Start.height/2) + r*Math.sin(radians));

            Start.iconList.get(i).setXY(x,y);

        }
    }

    public static void runFlower1(){


        setFlower1();

        for (int i = 1; i <59 ; i++) {

//            DesktopWindows.goToXY(Start.width/2,Start.height/2-50,i);
//            Start.iconList.get(i).setXY(100,100);
            Start.iconList.get(i).iconRunThread();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        folderV = true;
        momFoder();

        for (int i = 59; i <fl1 ; i++) {

            DesktopWindows.goToXY(Start.width/2,Start.height/2-50,i);
//            Start.iconList.get(i).setXY(100,100);
            Start.iconList.get(i).iconRunThread();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        folderV = false;

    }

    public  static void rrr(int n){
        int i = n;
        while(i<50){
            if(i%2==0){
                DesktopWindows.goToXY(Start.width/2 + 10,Start.height/2,0);
            }
            else {
                DesktopWindows.goToXY(Start.width/2 + -10,Start.height/2,0);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public static void main() {
        rrr(50);

        DesktopWindows.goToXY(Start.width/2 ,Start.height/2,0);
        clear();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        momFoder();
        rrr(3000);
        run83();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runFlower1();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clear();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runFlower2();
    }
}
