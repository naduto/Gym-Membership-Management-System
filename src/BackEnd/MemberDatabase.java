package BackEnd;

import java.util.*;
import java.io.*;

public class MemberDatabase extends DataBase<Member> {

    public MemberDatabase(String filename) throws IOException {
        super(filename);
        this.readFromFile();
    }

    @Override
    public Member createRecordFrom(String line) {
        String[] S = line.split(",");
        return new Member(S[0], S[1], S[2], S[3], S[4], S[5]);

    }

     @Override
    public boolean contains(String key) {
        for(Member it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean insertRecord(Member record) {
        
        if(this.contains(record.getSearchKey()))
        { 
            return false;
        }
        
        this.addrecord(record);
        return true;
    }
    @Override
     public Member getRecord(String key) {
        for(Member it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return it;
            }
        }
        return null;
    }
     @Override
    public boolean deleteRecord(String key) {
        for(Member it:this.returnAllRecords())
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
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getfilename()))) {
            for (Member record : this.returnAllRecords()) {
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
