package net.omidn.jclipboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JClipboard {

    private static ClipBoardHistory clipBoardHistory;
    private static HistoryWindow historyWindow;



    public static void main(String[] args) throws IOException {

        clipBoardHistory = new ClipBoardHistory();
        clipBoardHistory.start();

        historyWindow = HistoryWindow.create(clipBoardHistory);

        SystemTray systemTray = SystemTray.getSystemTray();
        BufferedImage trayIconImage = ImageIO.read(new File("src/main/resources/c.png"));
        int trayIconWidth = systemTray.getTrayIconSize().width;
        TrayIcon trayIcon = new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH));

        try {
            systemTray.add(trayIcon);
            trayIcon.addActionListener(e -> {
                historyWindow.showHistoryWindow();
            });
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }


}