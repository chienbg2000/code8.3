import com.sun.jna.platform.win32.WinDef;

import java.io.IOException;

public class IconDeskTop {
    public float x;
    public float y;
    public int itemIndex;
    public final int sizeHeart = 30;
    public final int step = 20;
    public final int delay = 60;

    public IconDeskTop() {
    }

    public IconDeskTop(int itemIndex){
        this.itemIndex = itemIndex;
    }
    public IconDeskTop(float x , float y , int itemIndex){
        this.x = x ;
        this.y = y ;
        this.itemIndex = itemIndex;
    }

    public void setXY(float x , float y){
        this.x = x ;
        this.y = y ;
    }

//    public void iconRun(){
//        WinDef.POINT  point = null;
//        try {
//            point = DesktopWindows.getItemPosition(itemIndex);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        float x0= point.x;
//        float y0= point.y;
//        DesktopWindows.goToXY(x0,y0, itemIndex);
//        float deltaX =x-x0;
//        float deltaY =y - y0;
//
//        float tan =(float) Math.abs(deltaY/deltaX);
//        int stepCount = (int)Math.abs (deltaX/step) + 3;
//        for (int i = 1; i < stepCount-2; i++) {
//            int stepX = step*i+1;
//            System.out.println(tan);
//
//            if(deltaX >0 && deltaY>0)
//                DesktopWindows.goToXY(x0 +stepX,y-(int)(tan*(deltaX-step*i)), itemIndex);
//            if(deltaX >0 && deltaY<0)
//                DesktopWindows.goToXY(x0 +stepX,y0 - (int)(tan*stepX), itemIndex);
//            if(deltaX <=0 && deltaY>0)
//                DesktopWindows.goToXY(x0 - stepX, y - (int)(tan*(Math.abs(deltaX)-stepX)), itemIndex);
//            if(deltaX <=0 && deltaY<0)
//                DesktopWindows.goToXY(x0 - stepX,y + (int)(tan*(Math.abs(deltaX)-stepX)), itemIndex);
//
//            try {
//                Thread.sleep(delay);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        DesktopWindows.goToXY(x,y, itemIndex);
//
//
//    }
    public void iconRun(){
        WinDef.POINT  point = null;
        try {
            point = DesktopWindows.getItemPosition(itemIndex);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float x0= point.x;
        float y0= point.y;
        DesktopWindows.goToXY((int)x0,(int)y0, itemIndex);
        float detaX = x-x0;
        float detaY = y-y0;

        float distance = (float) Math.sqrt(Math.pow(detaX,2)+Math.pow(detaY,2));

        float a = 0;
        float b = 0;
        if(detaX != 0 && detaY !=0){
            a = detaY/detaX;
            b = y0 - a*x0;
            // y = ax +b ;
        }

//        if(detaX ==0 && detaY != 0){
//            //x = x0;
//        }
//        if(detaX !=0 && detaY == 0){
//            //y= y0;
//        }


        float stepCount = distance/step;
        for (int i = 1; i < stepCount; i++) {
            if (detaX != 0 && detaY !=0){
                    x0 += detaX/stepCount;
                    y0 = (int)(a*x0 + b);
            }

            else if(detaX ==0 && detaY != 0){
                if (detaY>0)
                    y0 += step;
                if (detaY < 0)
                    y0 -= step;
                //x = x0;
            }
            else if(detaX !=0 && detaY == 0){
                if (detaX>0)
                    x0 += step;
                if (detaX < 0)
                    x0 -= step;
                //y= y0;
            }


            DesktopWindows.goToXY((int)x0,(int)y0, itemIndex);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DesktopWindows.goToXY((int)x,(int)y, itemIndex);


    }


    public void iconRunThread(){
        new Thread(){
            public void run(){
                iconRun();
            }
        }.start();
    }

    public void iconRunAndClearThread(){
        new Thread(){
            public void run(){
                iconRun();
                DesktopWindows.goToXY(2000,2000,itemIndex);
            }
        }.start();
    }

    public void iconGoToXY(){
        DesktopWindows.goToXY((int)x,(int)y, itemIndex);
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Icon{" +
                "x=" + x +
                ", y=" + y +
                ", ord=" + itemIndex +
                '}';
    }




}
