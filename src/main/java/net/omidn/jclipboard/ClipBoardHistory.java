package net.omidn.jclipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ClipBoardHistory extends Thread implements ClipboardOwner {

    private List<Transferable> history;
    private static final Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

    public ClipBoardHistory(){
        history = new LinkedList<>();
    }

    public void saveTransferable(Transferable transferable){
        history.add(transferable);
    }

    public List<Transferable> getHistory() {
        return history;
    }

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
        saveTransferable(t);
    }

    void regainOwnership(Transferable t) {
        sysClip.setContents(t, this);
    }


}
