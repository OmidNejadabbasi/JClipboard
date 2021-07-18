package net.omidn.jclipboard;

import java.awt.*;
import java.awt.geom.PathIterator;

public class HistoryWindow extends Window {
    private static HistoryWindow instance;

    private int height;
    private int width;
    private int posY;
    private int posX;

    public static HistoryWindow get(){
        if (instance==null){
            instance = new HistoryWindow();
        }
        return instance;
    }

    private HistoryWindow() {
        super(null, null);

        height = 500;
        width = 500;
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        posY = screenDimension.height - this.height - getTaskBarHeight();
        posX = screenDimension.width - this.width;
        System.out.printf("Taskbar size = %d%n", getTaskBarHeight());


    }


    public void showHistoryWindow() {


        this.setBounds(posX, posY, width, height); // will change later
        this.setVisible(true);
    }

    private static int getTaskBarHeight (){
        Dimension screeDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle maxWinSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return screeDimension.height - maxWinSize.height;
    }
}
