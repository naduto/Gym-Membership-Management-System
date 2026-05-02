package BackEnd;
import java.io.*;
import java.util.*;
import java.time.*;
public class MemberClassRegistrationDatabase extends DataBase<MemberClassRegistration>{

    public MemberClassRegistrationDatabase(String filename) throws IOException
    {
        super(filename);
        
        this.readFromFile();
        
    }
    @Override
    public MemberClassRegistration createRecordFrom(String line) {
       String[] S=line.split(",");
      MemberClassRegistration ret =new MemberClassRegistration(S[0],S[1],S[3]);
      
      ret.setRegistrationDate(LocalDate.parse(S[2].trim()));
       
       return ret;
    }
    @Override
    public boolean insertRecord(MemberClassRegistration record) {
        
        if(this.contains(record.getSearchKey()))
        {
            return false;
        }
        this.addrecord(record);
        return true;
    }
    @Override
     public MemberClassRegistration getRecord(String key) {
        for(MemberClassRegistration it:this.returnAllRecords())
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
        for(MemberClassRegistration it:this.returnAllRecords())
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
    public boolean contains(String key) {
        for(MemberClassRegistration it:this.returnAllRecords())
        {
            if(it.getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getfilename()))) {
            for (MemberClassRegistration record : this.returnAllRecords()) {
                String line = record.lineRepresentation();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
    
}
