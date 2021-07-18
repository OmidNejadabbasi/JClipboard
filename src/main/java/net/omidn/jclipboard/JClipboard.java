package net.omidn.jclipboard;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class JClipboard extends Thread implements ClipboardOwner {
    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    Repository repository = new Repository();

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

    public static void main(String[] args) {
        JClipboard b = new JClipboard();


        b.start();
    }
}