package projectJava.mainJava.sample.table;

import java.io.*;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 La tabella delle classi viene utilizzata per leggere e scrivere dati su un file
 */
public class Table {
    public static final String delimiter = ";";
    private static final String dataPath = System.getProperty("user.home") + File.separator + "ProjectProg3" + File.separator;

    private final String fileName;
    private RandomAccessFile file;
    private long lastPosition;

    /**
     Costruttore per la classe Table
     @param fileName nome del file da utilizzare
     */
    public Table(String fileName) {
        this.fileName = fileName;
        try {
            File dir = new File(dataPath);
            if (!dir.exists() && !dir.mkdir())
                throw new FileNotFoundException();

            file = new RandomAccessFile(dataPath + fileName, "rw");
            lastPosition = file.length();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Metodo per aggiungere un record al file
     @param data i dati da aggiungere al file
     */
    public void addRecord(String data) {
        try {
            file.seek(lastPosition);
            file.writeBytes(data + "\n");
            lastPosition = file.getFilePointer();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Metodo per selezionare i record dal file che soddisfano una condizione
     @param condition la condizione da testare rispetto a ogni record
     @return una HashMap contenente la posizione e il record
     coppia chiave-valore dell hashmap -> si intende posizione del record e il record stesso
     */
    public HashMap<Long, String> selectRecords(Predicate<String> condition) {
        HashMap<Long, String> selected = new HashMap<>();
        long pos = 0;

        try {
            file.seek(pos);
            while (pos < file.length()) {
                String record = file.readLine();
                if (condition == null || condition.test(record))
                    selected.put(pos, record);
                pos = file.getFilePointer();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return selected;
    }

    /**
     Metodo per aggiornare i record che soddisfano una determinata condizione
     @param condition la condizione da testare rispetto a ogni record
     @param newData i nuovi dati da scrivere nel file
     */
    public void updateRecords(Predicate<String> condition, Supplier<String> newData) {
        HashMap<Long, String> oldData = selectRecords(condition);
        for (long pos : oldData.keySet())
            update(pos, newData.get());
    }

    /**
     Metodo per aggiornare un record
     @param pos la posizione del record da aggiornare
     @param data i nuovi dati da scrivere
     */
    private void update(long pos, String data) {
        try {
            String tempFilePath = dataPath + "." + fileName + ".tmp";
            RandomAccessFile raf = new RandomAccessFile(tempFilePath, "rw");
            raf.seek(0);
            file.seek(0);
            while (file.getFilePointer() < file.length()) {
                if (file.getFilePointer() != pos){
                    raf.writeBytes(file.readLine() + "\n");}
                else {
                    raf.writeBytes(data + "\n");
                    file.readLine();
                }
            }
            raf.close();
            File tempFile = new File(tempFilePath);
            File originalFile = new File(dataPath + fileName);
            File oldFile = new File(dataPath + "." + fileName + ".old");
            if (!originalFile.renameTo(oldFile) || !tempFile.renameTo(originalFile) || !oldFile.delete()) {
                throw new RuntimeException("Updated failed");
            }
            file = new RandomAccessFile(originalFile,"rw");
            lastPosition = file.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
