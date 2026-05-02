package BackEnd;
import java.util.*;
import java.io.*;

public class TrainerDatabase extends DataBase<Trainer>{
    public TrainerDatabase(String filename) throws IOException
    {
        super(filename);
        this.readFromFile();
    }
    
    @Override
    public Trainer createRecordFrom(String line) {
       String[] S=line.split(",");
       return new Trainer(S[0],S[1],S[2],S[3],S[4]);
       
    }

   
    @Override
    public void saveToFile()
    {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.getfilename())))
        {
            for(Trainer record :this.returnAllRecords())
            {
                String line=record.lineRepresentation();
                writer.append(line);
                writer.newLine();
            }
        }
        catch(IOException e){
            System.out.println("error!!");
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(String key) {
        for(Trainer it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertRecord(Trainer record) {
        if(this.contains(record.getSearchKey()))
        {
            return false;
        }
        this.addrecord(record);
        return true;
    }

    @Override
    public Trainer getRecord(String key) {
        for(Trainer it:this.returnAllRecords())
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
        for(Trainer it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                this.removerecord(it);
                return true;
            }
        }
        return false;
    }
}
