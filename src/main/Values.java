package main;

import java.awt.*;

public class Values {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static double getWidth(){
        if(screenSize.getWidth()/1.8 >= 1065)
            return screenSize.getWidth()/1.8;
        return 1070.0;
    }
    public static double getHeight(){
        if(screenSize.getHeight()/1.6 >= 675)
            return screenSize.getHeight()/1.6;
        return 675;
    }

}
