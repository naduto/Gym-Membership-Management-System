package BackEnd;

import java.io.*;
import java.util.ArrayList;

public abstract class DataBase<T> {

    private ArrayList<T> records;
    private String filename;

    public DataBase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public String getfilename() {
        return this.filename;
    }

    public abstract T createRecordFrom(String line);


    public void readFromFile() throws FileNotFoundException, IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {

                T record = createRecordFrom(line);

                records.add(record);
            }
        }
    }
    public void addrecord(T record)
    {
        records.add(record);
    }
    public void removerecord(T record)
    {
        records.remove(record);
    }
    public ArrayList<T> returnAllRecords() {
        return this.records;
    }

    public abstract boolean contains(String key);

    public abstract boolean insertRecord(T record) ;

    public abstract T getRecord(String key) ;

    public abstract boolean deleteRecord(String key) ;

    public abstract void saveToFile();
}
