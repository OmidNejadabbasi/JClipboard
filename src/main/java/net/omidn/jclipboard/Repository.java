package net.omidn.jclipboard;

import java.awt.datatransfer.Transferable;
import java.util.LinkedList;
import java.util.List;

public class Repository {

    private List<Transferable> history;

    public Repository(){
        history = new LinkedList<>();
    }

    public void saveTransferable(Transferable transferable){
        history.add(transferable);
    }

    public List<Transferable> getHistory() {
        return history;
    }
}
