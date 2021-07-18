package net.omidn.jclipboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JClipboard extends Thread implements ClipboardOwner {
    private static final Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private static final Repository repository = new Repository();
    private static final HistoryWindow historyWindow = HistoryWindow.get();


    public void run() {
        Transferable trans = sysClip.getContents(this);
        regainOwnership(trans);
        System.out.println("Listening to board...");
        try {
            System.in.read(); // keep the app running
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lostOwnership(Clipboard c, Transferable t) {
        Transferable contents = sysClip.getContents(null); //EXCEPTION
        processContents(contents);
        regainOwnership(contents);
    }

    void processContents(Transferable t) {
        System.out.println("Processing: " + t);
        repository.saveTransferable(t);
    }

    void regainOwnership(Transferable t) {
        sysClip.setContents(t, this);
    }

    public static void main(String[] args) throws IOException {
        JClipboard b = new JClipboard();


        SystemTray systemTray = SystemTray.getSystemTray();
        BufferedImage trayIconImage = ImageIO.read(new File("src/main/resources/c.png"));
        int trayIconWidth = systemTray.getTrayIconSize().width;
        TrayIcon trayIcon = new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH));

        try {
            systemTray.add(trayIcon);
            trayIcon.addActionListener(e -> {
                historyWindow.showHistoryWindow(repository);
            });
        } catch (AWTException e) {
            e.printStackTrace();
        }

        b.start();
    }


}