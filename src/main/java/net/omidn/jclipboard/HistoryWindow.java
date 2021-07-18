package net.omidn.jclipboard;

import java.awt.*;

public class HistoryWindow extends Window {
    private static HistoryWindow instance;
    public static HistoryWindow get(){
        if (instance==null){
            instance = new HistoryWindow();
        }
        return instance;
    }

    private HistoryWindow() {
        super(null, null);



    }


    public void showHistoryWindow() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenDimension);

        this.setBounds(500, 500, 500, 500); // will change later
        this.setVisible(true);
    }


}
