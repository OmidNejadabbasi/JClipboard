package net.omidn.jclipboard;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HistoryWindow extends Window {
    private static HistoryWindow instance;
    private JList<Transferable> historyJList;

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


    public void showHistoryWindow(Repository repository) {


        this.setBounds(posX, posY, width, height); // will change later
        this.setVisible(true);
        this.historyJList = new JList<>();
        historyJList.setCellRenderer(new HistoryListCellRenderer());
        historyJList.setListData(repository.getHistory().toArray(new Transferable[0]));
        this.add(historyJList);
    }

    private static int getTaskBarHeight (){
        Dimension screeDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle maxWinSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return screeDimension.height - maxWinSize.height;
    }

    private static class HistoryListCellRenderer implements ListCellRenderer<Transferable> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Transferable> list, Transferable value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component=null;
            if (value.isDataFlavorSupported(DataFlavor.stringFlavor)){

                JLabel label = new JLabel();
                try {
                    label.setText((String) value.getTransferData(DataFlavor.stringFlavor));
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                component = label;
            }

            return component;
        }
    }


}
