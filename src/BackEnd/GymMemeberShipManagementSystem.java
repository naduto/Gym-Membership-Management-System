package BackEnd;

import java.io.IOException;


public class GymMemeberShipManagementSystem {

 public static void main(String[] args) throws IOException {
        MemberClassRegistration t=new MemberClassRegistration("M166","C102","active");
        MemberClassRegistration t2=new MemberClassRegistration("M122","C104","Active");
        
        MemberClassRegistrationDatabase D=new MemberClassRegistrationDatabase("Registration.txt");
        
        boolean n=D.insertRecord(t);
        D.saveToFile();
        for(MemberClassRegistration record:D.returnAllRecords())
        {
            System.out.println(record.getSearchKey());
            System.out.println(record.lineRepresentation());
        }
    }
}
