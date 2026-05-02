package BackEnd;

import java.util.*;
import java.io.*;

public class ClassDatabase extends DataBase<Class> {

    public ClassDatabase(String filename) throws IOException {
        super(filename);
        this.readFromFile();
    }
    
    @Override
    public Class createRecordFrom(String line) {
         
        String[] S = line.split(",");
        int duration=Integer.parseInt(S[3].trim());
        int seats=Integer.parseInt(S[4].trim());
         Class ret=new Class(S[0], S[1], S[2],duration,seats);
         
         return ret;
         
    }

     @Override
    public boolean contains(String key) {
        for(Class it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean insertRecord(Class record) {
        if(this.contains(record.getSearchKey()))
        {
            return false;
        }
        this.addrecord(record);
        return true;
    }
    @Override
    public boolean deleteRecord(String key) {
        for(Class it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                this.removerecord(it);
                return true;
            }
        }
        return false;
    }
    @Override
     public Class getRecord(String key) {
        for(Class it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return it;
            }
        }
        return null;
    }
    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getfilename()))) {
            for (Class record : this.returnAllRecords()) {
                String line = record.lineRepresentation();
                writer.append(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("error!!");
            e.printStackTrace();
        }
    }

}
